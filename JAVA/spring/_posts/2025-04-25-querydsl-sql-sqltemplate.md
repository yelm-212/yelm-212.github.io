---
title:  "[Java Spring] [QueryDSL-SQL] QueryDSL-SQL SQLTemplates 설정 문제로 인한 생성 쿼리 Grammar Error"
excerpt: "QueryDSL-SQL 설정 문제로 인한 생성 쿼리 Grammar Error 해결 과정에 대해 작성하였습니다."

tags:
  - [Blog, Spring, QueryDSL-SQL, QueryDSL]

toc: true
toc_sticky: true
 
date: 2025-04-25
last_modified_at: 2025-04-25
---
# QueryDSL-SQL SQLTemplates 설정 문제 해결

  

## 문제 상황

- QueryDSL을 사용하여 데이터베이스 쿼리를 생성할 때, 생성 쿼리에 Bad sql grammar error가 발생하는 문제가 발생하였다. 
- 특히 `where` 절에서 boolean 조건이 데이터베이스 벤더에 맞지 않는 형식으로 생성되는 현상이 있었다.
	- 예를 들어, PostgreSQL에서는 `true/false`로 처리되어야 하는데 `1/0`으로 처리되는 등의 불일치가 생겼다.


## 원인 정의

1. **`SQLTemplates` 설정이 데이터베이스 벤더별 특성을 제대로 반영하지 못하였다.**

	```java
	        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(SQLTemplates.DEFAULT);
	```
	
	위와 같이 Config를 설정하면, 각기 다른 DB 벤더를 구동하도록 설정하였을때 런타임에 알아서 적절한 `SQLTemplate` 객체를 선택해 작동하는 줄 알았는데 아니었다. 결국 템플릿 설정은 직접 해줘야 하는 거였다. 
	
	![](/attatchments/img20250425105915.png)
	
	[querydsl-sql에서는 사진상 벤더들을 지원](http://querydsl.com/static/querydsl/4.0.0/reference/ko-KR/html/ch02s03.html)하고 있다.

2. 알 수 없는 데이터베이스 벤더에 대한 fallback 처리가 부적절
	- `SQLTemplates`를 직접 인스턴스화하려 시도했으나, 이는 추상 클래스이므로 불가능하다.


따라서 런타임에 벤더 metadata 정보를 감지해 이에 따라 `SQLTemplates`를 선택하도록 구현하였다.
  

## 해결 방안

1. 벤더별 `SQLTemplates` 생성자 파라미터 활용
2. 데이터베이스 자동 감지 로직 구현 (`DatabaseMetaData`)
3. 안전한 Fallback 처리
	- 알 수 없는 데이터베이스 벤더나 오류 발생 시 `H2Templates`를 기본값으로 사용하도록 하였다.
4. 추가 설정
	- Exception Translator 설정으로 스프링의 예외 처리 통합
	- Literal 사용 설정으로 prepared statement 대신 직접 값을 쿼리에 포함시키도록 하였다.

다음은 위 사항을 반영한 QueryDSL-SQL Config 파일의 일부이다.

```java
	@Bean  
	public SQLQueryFactory sqlQueryFactory(DataSource dataSource) {  
	    SQLTemplates templates = createSQLTemplates(dataSource);  
	    com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);  
	    configuration.setExceptionTranslator(new SpringExceptionTranslator());  
	    configuration.setUseLiterals(true);  
	      
	    return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));  
	}  
	  
	private SQLTemplates createSQLTemplates(DataSource dataSource) {  
	    try (Connection conn = dataSource.getConnection()) {  
	        DatabaseMetaData metaData = conn.getMetaData();  
	        String databaseProductName = metaData.getDatabaseProductName().toLowerCase();  
	          
	        log.info("Detected database: {}", databaseProductName);  
	          
	        if (databaseProductName.contains("postgresql")) {  
	            return new PostgreSQLTemplates(true);  // true for quote identifiers  
	        } else if (databaseProductName.contains("mysql")) {  
	            return new MySQLTemplates(true);  
	        } else if (databaseProductName.contains("oracle")) {  
	            return new OracleTemplates(true);  
	        } else if (databaseProductName.contains("h2")) {  
	            return new H2Templates(true);  
	        }  
	          
	        log.warn("Unknown database vendor: {}. Using H2Templates as default", databaseProductName);  
	        return new H2Templates(true);  
	          
	    } catch (SQLException e) {  
	        log.error("Failed to detect database vendor. Using H2Templates as default", e);  
	        return new H2Templates(true);  
	    }  
	}
```

## 결과

- 데이터베이스 벤더별로 적절한 boolean 값 처리가 가능해졌다.
- 자동으로 데이터베이스 타입 감지 및 적절한 템플릿 적용이 가능해졌다.
- 예외 상황에 대한 안전한 폴백 처리를 구현하였다.

이러한 설정을 통해 QueryDSL-SQL을 통해 런타임에 각 데이터베이스를 감지하고, 벤더에 적절한 SQL을 생성하도록 보장할 수 있게 하였다.