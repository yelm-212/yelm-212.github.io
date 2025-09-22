---
permalink: /about/
layout: splash
# classes: wide
title: " "
toc: true
toc_sticky: true
toc_label: "YRSHIN"
last_modified_at: 2025-01-07
---

<br>


# [Backend Engineer] Yerim Shin

<div style="display: flex !important; gap: 30px !important; align-items: flex-start !important; width: 100% !important; max-width: none !important;">
<div style="flex: 1 !important; min-width: 0 !important;">
<h3>학습과 경험을 기록하고, 실무 적용과 코드 개선으로 이어가는 개발자</h3>
<ul>
<li>새로운 기술과 개념을 단순히 정리하는 데 그치지 않고, <br>
실무에서 활용하거나 기존 코드 개선에 적용할 방법을 고민합니다.</li>
</ul>
</div>
<div style="flex: 1 !important; min-width: 0 !important;">
<h3>Contacts</h3>
<p> <a href="mailto:21yrshin@proton.me">E-Mail</a></p>
<p> <a href="https://github.com/yelm-212">GitHub</a></p>
<p> <a href="https://yelm-212.github.io/">GitHub blog</a></p>
</div>
</div>

---

# <span style="color:#0070c0">Skills</span>

## **Back-End**
- Java, Python
- Spring Boot, Flask

## Infra
- MySQL, MongoDB, PostgreSQL
- AWS
- Jenkins
- Docker

## Tools
- IntelliJ IDEA, PyCharm, DataGrip
- Visual Studio Code
- BitBucket, Slack, Confluence, Jira
- GitHub, GitLab

---

# <span style="color:#0070c0">Experiences</span>

## 에이데이타 | 2025-01~

자사 솔루션 고도화 및 유지보수, 신규 솔루션 연구개발

### 기능 구현 및 역할

#### SCV (Smart Collection Vehicle, 빅데이터 수집, 연계 솔루션)
- 기존 Vue3 + JSP formlogin 혼용 인증 구조를 Spring Security + JWT 기반 인증 구조로 마이그레이션
  - `Pinia` 상태 관리 및 `HttpOnly 쿠키` 기반 인증/인가 로직 구현
    - 사용자 인증 후 JWT를 `HttpOnly` 쿠키에 저장하고, Spring Security 인증 컨텍스트에 사용자 정보를 유지
  - 사용자 권한(Role)에 따라 접근 가능한 페이지 및 컴포넌트 구성을 서버에서 분기해 제공
    - Pinia를 활용해 클라이언트 상태 관리 및 서버에서 전달된 역할 기반 페이지 설정 로직 구현

**기술 스택**
- Java, Spring Boot (Spring Security, JJWT, MyBatis)
- PostgreSQL, Redis / Caffeine
- Vue3 (Vue Router, Pinia, Axios)

**버전 관리 및 CI / CD**
- GitLab, Jenkins

#### 신규 솔루션 개발 (API Management 솔루션)
- 다수의 백엔드 서비스를 Spring Cloud Gateway로 통합
  - 기존 N개 단일 서버로 요청 → 통합 API Gateway + 서비스 분리 구조로 전환
- QueryDSL-SQL / QueryDSL-JPA 혼용을 통한 DB 벤더 무관 동적 쿼리 처리 모듈 구현
  - MySQL, PostgreSQL 등 다양한 RDBMS 환경에서 스키마 조회 및 동적 쿼리 실행
  - 보안상 제한적 사용을 전제로 설계, 운영 정책에 따라 접근 제어 적용

**기술 스택**
- Java, Spring Boot (Spring Gateway, QueryDSL-SQL, QueryDSL-JPA)
- PostgreSQL, H2
- Vue3 (Vue Router, Pinia, Axios)

**버전 관리 및 CI / CD**
- GitLab, Jenkins

## [더커머스(원셀)](https://www.onesell.co.kr) | 2024-06~2024-12

셀러가 다양한 쇼핑 플랫폼에서 상품과 주문을 관리할 수 있는 통합 관리 솔루션 운영 및 개발

### 기능 구현 및 역할

- **상품 관리 시스템 개선**
  - Java Spring Boot 애플리케이션의 데이터 전송 및 저장 모듈 유지보수
    - 상품 판매 상태 및 재고 관리를 위한 REST API 설계 및 구현
  - AWS Lambda(Python) 상품 수정 모듈 개선
    - 기존에 단일 API로 처리되는 상품 수정 로직을 상태별로 분리
    - 가격/재고/판매상태 변경에 따른 최적화된 API 호출 구현

- **네이버 스마트스토어 추가상품 수집 및 지그재그 추가상품 등록**
  - 기존 프론트엔드 컴포넌트와의 데이터 인터페이스 연동
  - 추가상품이 있는 상품의 수집 기능 신규 구현
  - 네이버 스마트스토어 상품 데이터 API 연동
    - Flask 서버를 통한 추가상품 포함 데이터 수집
    - 수집된 데이터를 솔루션 상품 형식으로 변환
  - 지그재그 API 연동 및 상품 등록
    - Java Spring 서버에서 지그재그 API를 통한 상품 등록 구현
  - 멀티 쇼핑몰 등록 지원
    - 수집된 추가상품 데이터를 에이블리, G마켓 등 타 플랫폼에 등록 가능하도록 구현
    - 플랫폼 간 상품 등록 일관성 확보

- **EXCEL 간편등록기 상품 등록 개선**
  - Java Spring Boot 애플리케이션의 데이터 전송 및 저장 모듈 유지보수
    - 변환된 데이터를 받아 데이터베이스(MongoDB)에 저장하는 기능을 수정하고 최적화

- **지그재그 간편 등록 솔루션 무료이용권 관리 시스템 구현**
  - 운영 서버 배포 시 자동화된 이용권 만료 처리 시스템 구현
  - 대상 데이터(10,000건)의 일괄 처리 시스템 구축
    - MongoDB `bulkWrite`를 활용한 데이터 업데이트 처리
    - REST API 명세 설계 및 프론트엔드 연동 구현

- **원셀 상품 스마트스토어 업로드 요청 처리 개선**
  - OpenAPI 호출 시 피크 시간대 **429** 응답 코드로 인한 상품 등록 요청 건의 실패 문제 (약 전체의 40%) 개선
  - 응답 헤더 분석을 통한 동적 대기 시간 설정 및 자동 재시도 로직 구현
  - 피크 시간대 업로드 성공률 90% 이상 달성

### 기술 스택
- Java, Spring Boot
- Python, Flask
- MongoDB
- Microservice Architecture
- AWS (EC2, SQS, ECS, Lambda, VPC, CloudWatch …)
- Jenkins
- Bitbucket

---

# <span style="color:#0070c0">Projects</span>

## Open Source Contributions

### [Spring Security](https://github.com/spring-projects/spring-security)
- **Issue:** [Replace Hardcoded 403 with HttpStatus.FORBIDDEN.value() #16615](https://github.com/spring-projects/spring-security/issues/16615) **(Closed)**
- **PR:** [Refactored Http403ForbiddenEntryPoint #16616](https://github.com/spring-projects/spring-security/pull/16616) **(Merged)**
- 기존 코드에서 **HTTP 상태 코드** `403`과 오류 메시지 `"Access Denied"`가 **하드코딩**되어 있는 문제를 발견
  - 이를 `HttpStatus.FORBIDDEN.value()` 및 `HttpStatus.FORBIDDEN.getReasonPhrase()`를 사용하도록 변경하여 **유지보수성을 개선**
- Maintainer의 피드백을 반영하여 **copyright 연도를 2025로 업데이트**하는 등 추가 개선을 수행

## Spring + Vue3 Login Demo Project

Spring Security + JWT + Blacklist Logout을 구현한 Demo Project

- [Spring (Server) GitHub Repository](https://github.com/yelm-212/spring-jwt-login-logout)
- [Vue3 (Client) GitHub Repository](https://github.com/yelm-212/jwt-login-client)

### 기능 구현
- Login & Logout 기능 구현
  - Refresh Token & Access Token Authorization
- General User Endpoint API 구현
- Admin-only Endpoint API 구현
- Token Reissue 기능 구현
- 개별 GitHub Repository의 README.md 작성

### 기술 스택
- Java, Spring Boot (Spring Security, JJWT, PostgreSQL, Redis / Caffeine)
- Vue3, TypeScript (Element Plus, Vue Router, Pinia, Axios, Vite)
- GitHub
- Docker

## 모바일이앤엠애드(인턴) 프로젝트 | 2023-11 ~ 2024-01

자사 홈페이지를 관리하는 웹 어플리케이션

### 기능 구현 및 역할
- **Google Analytics Data API를 사용한 웹사이트 통계 기능 구현**
  - 기간 선택해 접속 현황(전체 페이지뷰, 방문자 수) 조회
  - 기간 선택해 유입 경로 분석(유입 URL, 유입 검색어, 유입 검색 엔진) 조회
  - chart.js 적용해 그래프 뷰 페이지 구현

- **팝업 및 게시판의 이미지 처리**
  - File I/O 방식을 통해 서버에 직접 저장

- **REST API 구현**
  - 회사 홈페이지의 게시판 및 팝업 CRUD API
    - JPA, QueryDSL 적용
    - 기존 퍼블리싱된 회사 홈페이지에 구현 API 적용
    - 게시판 조회수 기능

- **관리자 페이지 화면 구성 및 API 구현**
  - 관리자(유저) 로그인 토큰 발급

- **Spring Security & JWT**
- **DB 스키마 설계 및 API 문서화**

### 기술 스택
- Java, Spring Boot, HTML&CSS&JavaScript
- MySQL, MariaDB
- Gogs, GitHub

---

# Educations

- **경북대학교 졸업** \| 2019-02 ~ 2023-08
- **컴퓨터학부 복수전공** \| 산림과학.조경학부 임산공학전공

# Certifications

- ISTQB CTFL \| 2021-07
- SQLD \| 한국데이터산업진흥원 \| 2024-06
- 정보처리기사 \| 한국산업인력공단 \| 2025-09
