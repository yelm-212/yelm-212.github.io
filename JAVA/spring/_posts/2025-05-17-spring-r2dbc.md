---
title:  "[Java Spring] Spring WebFlux 환경에서 JDBC를 쓰지 않고 R2DBC를 사용하는 이유"
excerpt: "Spring R2DBC와 Servlet JDBC를 비교 및 구현 원리에 대해 간략하게 정리하였습니다."

tags:
  - [Blog, JDBC, R2DBC, Spring]

toc: true
toc_sticky: true
 
date: 2023-05-17
last_modified_at: 2023-05-17
---

# Spring WebFlux 환경에서 JDBC를 쓰지 않고 R2DBC를 사용하는 이유

## Spring WebFlux 환경이란?

- **Non-blocking I/O** 기반의 **비동기** 웹 프레임워크
- 적은 수의 스레드(Event Loop)로 많은 요청을 처리하는 구조로 되어 있다.
- **Reactive Streams** (`Mono`, `Flux`)를 사용해 데이터 흐름을 관리한다.

---

## JDBC가 WebFlux 환경에 적합하지 않은 이유

### Blocking I/O 방식
- JDBC는 기본적으로 **동기적인 방식**으로 동작한다.
- 요청한 SQL 결과를 받을 때까지 **스레드를 대기(blocking)**시킴
- WebFlux는 비동기(non-blocking) 방식이라 충돌이 발생하게 된다.

### 스레드 효율성 저하
- WebFlux의 이벤트 루프 방식에서는 **소수의 스레드로 많은 요청을 처리**해야 함
- JDBC의 blocking으로 인해 이벤트 루프가 **대기 상태로 전환**, 다른 요청 처리 불가
- 결과적으로 성능 저하 및 리소스 낭비 발생

### Backpressure 적용 불가
- WebFlux는 Reactive Streams를 통해 요청 처리를 유연하게 조절하는 **Backpressure** 지원
- JDBC는 이 개념을 지원하지 않아, DB 병목이 생길 경우 요청이 쌓이게 된다.

---

## R2DBC (Reactive Relational Database Connectivity)

- R2DBC는 RDB 환경에서도 **Non-blocking Reactive 방식**을 제공하는 API 표준으로, JDBC의 Blocking 문제를 해결하기 위해 설계되었다.

### R2DBC의 Non-blocking 구조 원리

|항목|JDBC|R2DBC|
|---|---|---|
|**네트워크 통신**|동기 네트워크 I/O (Blocking)|비동기 네트워크 I/O (Non-blocking)|
|**결과 데이터 처리**|`ResultSet.next()` 호출 시 대기|Reactive Streams를 활용한 비동기 데이터 스트리밍|
|**커넥션 관리**|Blocking Connection Pool (ex. HikariCP)|비동기 Connection Pool (ex. `r2dbc-pool`)|
|**트랜잭션 처리**|`commit()` 호출 시 Blocking|Reactive Transaction API로 Non-blocking 처리|

### 비동기 네트워크 프로토콜 사용

- R2DBC는 드라이버 단계에서 DB와 **비동기 네트워크 프로토콜**로 통신한다.
- 응답이 도착할 때까지 기다리지 않고, 다른 작업 수행이 가능하다.

### Publisher-Subscriber 패턴을 통한 데이터 스트리밍

- DB의 결과 데이터를 **스트리밍 방식**으로 처리한다.

```java
// JDBC 방식 (Blocking)
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
while (rs.next()) {
    String name = rs.getString("name"); // Blocking 호출
    System.out.println(name);
}

// R2DBC 방식 (Non-blocking)
databaseClient.sql("SELECT * FROM users")
    .map((row, metadata) -> row.get("name", String.class))
    .all()
    .subscribe(name -> System.out.println(name));
```

### 비동기 Connection Pool 사용

- JDBC 방식과 달리 커넥션 풀을 비동기 방식으로 관리하여 블로킹 최소화

---

## 결론 

- Spring WebFlux에서는 **Blocking I/O 기반의 JDBC 사용이 부적합**함
- 이를 해결하기 위해 R2DBC를 통해 비동기(Non-blocking) 방식으로 RDB를 효율적으로 사용 가능
- WebFlux 프로젝트에서는 R2DBC 기반 데이터베이스 접근을 권장하고 있다.
- RDBMS는 본래 정합성을 위해 블로킹 방식으로 대기하지만, R2DBC는 **Pub-Sub 패턴**과 **비동기 네트워크 프로토콜**을 사용하여 이 문제를 해결한다.

### R2DBC 구현체

- PostgreSQL: [`r2dbc-postgresql`](https://github.com/pgjdbc/r2dbc-postgresql)
- MySQL: [`r2dbc-mysql`](https://github.com/mirromutth/r2dbc-mysql)
- H2: [`r2dbc-h2`](https://github.com/r2dbc/r2dbc-h2)