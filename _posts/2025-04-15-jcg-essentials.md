---
title:  "정보처리기사 실기 필수 암기 내용 정리"
excerpt: "정보처리기사 실기 필수 암기 요소에 대해 간략하게 정리했습니다."

tags:
  - [정보처리기사]

toc: true
toc_sticky: true
 
date: 2025-04-15
last_modified_at: 2025-04-15

---

## 📌 형상관리

**식통감기**

소프트웨어 개발 과정에서 산출물의 변경 사항을 체계적으로 관리하는 절차

- **형상식별**: 관리할 항목 정의
- **형상통제**: 변경 요청 관리 및 승인
- **형상감사**: 변경 이력 및 산출물 상태 점검
- **형상기록**: 형상 항목과 변경 사항 문서화
  

## 📌 정규화 단계

**도부이결다조**

데이터 중복을 줄이고 이상 현상을 방지하기 위한 관계형 데이터베이스 설계 기법

- **도메인 분해**: 컬럼 값이 유효한 도메인에 속하는지
- **부분 함수 종속 제거 (2NF)**: 기본키의 일부에만 종속된 속성 제거
- **이행 함수 종속 제거 (3NF)**: 비주요 속성이 다른 비주요 속성에 종속된 것 제거
- **결정자가 후보키가 아닌 것 제거 (BCNF)**
- **다치 종속 제거 (4NF)**: 하나의 속성이 여러 값 가질 때 다른 속성들과 독립적일 경우 분리
- **조인 종속 제거 (5NF)**: 조인으로만 복원 가능한 관계 제거

  

## 📌 OSI 7계층

**응표세전네데물**

컴퓨터 네트워크 통신의 계층적 모델

1. **응용 계층**: 사용자 인터페이스 제공
2. **표현 계층**: 데이터 형식 변환 및 암호화
3. **세션 계층**: 연결 설정, 유지, 종료
4. **전송 계층**: 신뢰성 있는 데이터 전송 (TCP/UDP)
5. **네트워크 계층**: 경로 설정 및 IP 주소
6. **데이터링크 계층**: 물리 주소(MAC) 기반 데이터 전송
7. **물리 계층**: 전기적/물리적 신호 전달

| 계층 번호 | 이름         | 키워드           | 역할 요약                           | 대표 프로토콜                                  |
|-----------|--------------|------------------|--------------------------------------|------------------------------------------------|
| 7         | 응용층       | 사용자 인터페이스 | **사용자**가 네트워크에 접근 (웹, 메일 등)   | HTTP, HTTPS, FTP, SMTP, IMAP, DNS, SNMP, SSH, DHCP  |
| 6         | 표현층       | 변환, 암호화       | **데이터 형식** 변환, 암호화, 압축 등         | TLS, SSL, JPEG, MPEG, ASCII, EBCDIC             |
| 5         | 세션층       | 연결 관리         | **세션** 열고 닫고, 대화 유지                | NetBIOS, RPC, PPTP                             |
| 4         | 전송층       | 신뢰성, 순서       | **데이터 전달** 보장 (TCP/UDP)             | TCP, UDP, SCTP                                 |
| 3         | 네트워크층   | 경로, 주소         | IP 주소로 목적지까지 **경로 설정**            | IP, ICMP, IGMP, IPsec, OSPF, BGP                |
| 2         | 데이터링크층 | 프레임, MAC       | **MAC 주소**로 이웃 **장비와 통신**             | Ethernet, PPP, Frame Relay, ARP                |
| 1         | 물리층       | 전기 신호         | 전기/광 신호로 비트 전송                | RS-232, DSL, USB, IEEE 802.11, 광케이블         |



## 📌 XP 가치

**의사피존용단**

익스트림 프로그래밍에서 중시하는 개발자 간 핵심 가치

- **의사소통**: 적극적인 커뮤니케이션
- **피드백**: 즉각적이고 자주 피드백
- **존중**: 팀원 간의 존중
- **용기**: 변화를 두려워하지 않음
- **단순성**: 단순한 설계 지향


## 📌 **<span style="color: blue">결합도</span>**

**내공외제스자**

**각 개념 내용 좀 봐야함 몇번 틀림**

모듈 간 의존성 정도 (낮을수록 좋음)

1. **내용 결합**: 내부 구조를 직접 참조 (최악)
2. **공통 결합**: 전역변수 공유
3. **외부 결합**: 외부 인터페이스 공유
4. **제어 결합**: 제어 정보를 인자로 전달
5. **스탬프 결합**: 구조체 일부만 사용
6. **자료 결합**: 필요한 데이터만 인자로 전달 (최선)

  

## 📌 **<span style="color: blue">응집도</span>**

**우논시절통순기**

모듈 내부 구성요소 간 관련성 (높을수록 좋음)

**각 개념 내용 좀 봐야함 몇번 틀림**

1. **우연적 응집**: 관련 없는 작업 묶음 (최악)
2. **논리적 응집**: 논리적으로 유사한 기능 묶음
3. **시간적 응집**: 특정 시간에 실행될 작업 묶음
4. **절차적 응집**: 순서대로 실행될 기능 묶음
5. **통신적 응집**: 동일한 데이터 사용
6. **순차적 응집**: 한 작업의 결과가 다음 입력
7. **기능적 응집**: 하나의 기능에 집중 (최선)

  

## 📌 요구사항 개발 프로세스

**도분명확**

소프트웨어 요구사항을 정의하고 정제하는 절차

1. **도출**: 이해관계자와 요구사항 수집
2. **분석**: 모순·중복 제거
3. **명세**: 문서화 및 표현
4. **확인**: 타당성 검토

  

## 📌 객체지향 분석 기법

**럼바우객동기**

럼바우 방법론은 객체지향 시스템 분석의 대표적 방법

1. **객체 모델링**: 클래스와 속성, 관계 표현
2. **동적 모델링**: 상태 변화 표현
3. **기능 모델링**: 데이터 흐름 및 기능 표현

  

## 아키텍처 4+1 관점

**논구프배유**

**각 개념 내용 좀 봐야함 몇번 틀림**

시스템을 다각도에서 설명하기 위한 방법

1. **논리적**: 클래스, 객체
2. **구현**: 소스코드 구조
3. **프로세스**: 동시성, 스레드
4. **배치**: 실행환경
5. **유스케이스**: 사용자 요구사항

  

## 📌 **<span style="color: blue">정적 다이어그램</span>**

**클객컴배복패**

시스템 구조를 보여주는 UML 다이어그램

1. **클래스 다이어그램**
   - 클래스의 구조와 관계를 표현
   ```mermaid
   classDiagram
       class User {
           -id: String
           -name: String
           +login()
           +logout()
       }
       class Account {
           -balance: int
           +deposit(amount)
           +withdraw(amount)
       }
       User "1" -- "1..*" Account
   ```

2. **객체 다이어그램**
   - 클래스의 인스턴스를 표현
   - 예시: User 클래스의 인스턴스 user1(id="user123", name="홍길동")과 Account 클래스의 인스턴스 account1(balance=10000) 간의 관계

![](https://www.ibm.com/docs/ko/SS4JE2_7.5.5/com.ibm.xtools.modeler.doc/images/sm_objectdiag1.gif)

3. **컴포넌트 다이어그램**
   - 시스템의 컴포넌트 구조를 표현
   - 예시: Web UI → Business Logic → Database 컴포넌트 간의 의존 관계

![](https://velog.velcdn.com/images/gun_123/post/3a15b362-4add-42ea-a92c-0474040a647a/image.png)

4. **배치 다이어그램**
   - 물리적 아키텍처를 표현
   - 예시: Web Server(Web App)와 Database Server(Database) 간의 TCP/IP 연결

![](https://www.ibm.com/docs/ko/SS8PJ7_9.6.1/com.ibm.xtools.modeler.doc/images/cdepd.gif)

5. **복합체 구조 다이어그램**
   - 클래스 내부 구조를 표현
   ```mermaid
   classDiagram
       class Computer {
           +CPU cpu
           +Memory memory
           +Storage storage
       }
       class CPU
       class Memory
       class Storage
       Computer *-- CPU
       Computer *-- Memory
       Computer *-- Storage
   ```

6. **패키지 다이어그램**
   - 패키지 간의 관계를 표현
   - 예시: UI 패키지(UserController, UserView) → Domain 패키지(UserService, UserRepository) → Infrastructure 패키지(Database, Cache) 간의 의존 관계

![](https://www.ibm.com/docs/ko/SS4JE2_7.5.5/com.ibm.xtools.modeler.doc/images/package.gif)

## 📌 동적 다이어그램

**유시커상활상타**

시스템의 동작 흐름 표현

- 유스케이스, 시퀀스, 커뮤니케이션, 상태, 활동, 타이밍

  

## 📌 Cron 구성

**분시일월요일 명령어**

- 일정한 주기로 명령어 실행하기 위한 스케줄러 표현
  

## 📌 EAI 유형

기업 애플리케이션 통합 방식

1. **Point to Point**: 1:1 직접 연결
2. **Hub & Spoke**: 중앙 허브 중계
3. **Message Bus**: 메시지 버스 활용
4. **Hybrid**: 혼합 구조

  

## 📌 SOLID 원칙

객체지향 설계의 5대 원칙

- Single Responsibility Principle: 하나의 책임만
- Open/Closed Principle: 확장엔 열려 있고 수정엔 닫힘
- Liskov Substitution Principle: 자식 클래스는 부모 대체 가능
- Interface Segregation Principle: 클라이언트는 자신이 사용하지 않는 메서드에 의존 X
- Dependency Inversion Principle: 고수준 모듈은 저수준 모듈에 의존 X


## 📌 디자인 패턴

### 생성 패턴 (Creational Patterns)
**생구행 - 생빌프로팩앱싱**

객체 생성을 추상화하는 패턴

- Builder: 복잡한 객체의 생성 과정을 단계별로 분리
- Prototype: 객체를 복제하여 새로운 객체 생성
- Factory Method: 객체 생성을 서브클래스에 위임
- Abstract Factory: 관련 객체들을 생성하는 인터페이스 제공
- Singleton: 클래스의 인스턴스를 하나만 생성

### 구조 패턴 (Structural Patterns)
**구어데프브플컴**

클래스나 객체를 조합하여 더 큰 구조를 만드는 패턴

- Adapter: 호환되지 않는 인터페이스를 연결
- Decorator: 객체에 동적으로 기능 추가
- Facade: 복잡한 서브시스템에 대한 간단한 인터페이스 제공
- Bridge: 구현부와 추상부를 분리하여 독립적으로 확장
- Flyweight: 공유를 통해 많은 객체를 효율적으로 지원
- Composite: 객체들을 트리 구조로 구성하여 부분-전체 계층 표현
- Proxy: 다른 객체에 대한 접근을 제어

### 행위 패턴 (Behavioral Patterns)
**행상템메옵스전반중책방**

객체 간의 상호작용과 책임 분배를 다루는 패턴

- Strategy: 알고리즘을 캡슐화하여 교체 가능하게 만듦
- Template Method: 알고리즘의 구조를 정의하고 일부 단계를 서브클래스에 위임
- Mediator: 객체 간의 복잡한 상호작용을 캡슐화
- Memento: 객체의 상태를 저장하고 복원
- Observer: 객체의 상태 변화를 다른 객체에 알림
- State: 객체의 내부 상태에 따라 행동을 변경
- Visitor: 객체 구조에 대한 연산을 분리
- Command: 요청을 객체로 캡슐화
- Iterator: 컬렉션의 요소에 접근하는 방법을 제공
- Chain of Responsibility: 요청을 처리할 수 있는 객체들을 연결
- Interpreter: 문법을 정의하고 해석

## 📌 테스트 오라클

**참샘휴일**

테스트 결과의 참·거짓을 판단하는 기준

- 참오라클: 모든 입력에 대해 정답을 알고 있음
- 샘플링오라클: 일부 입력만 정답
- 휴리스틱오라클: 경험 기반
- 일관성검사오라클: 시스템 자체 결과 비교

  

## 📌 테스트 단계

**단통시인**

개발 주기에 따라 진행되는 테스트 단계

1. 단위 테스트
2. 통합 테스트
3. 시스템 테스트 
4. 인수 테스트 

  

## 📌 상향식/하향식 통합 테스트

**상드하스**

- 상향식: 하위 → 상위 (드라이버 필요)
- 하향식: 상위 → 하위 (스텁 필요)

## 📌 **<span style="color: blue">테스트 기준</span>**

**다중조건 선택검증 조건검증 결정검증 결정/조건 구문**

- **다중조건 검증 (Multiple Condition Coverage)**: 모든 조건 조합 검증
- **선택검증 (Selection Coverage)**: 조건문의 각 분기 검증
- **조건검증 (Condition Coverage)**: 각 조건의 참/거짓 검증
- **결정검증 (Decision Coverage)**: 전체 조건문의 참/거짓 검증
- **결정/조건 검증 (Decision/Condition Coverage)**: 결정과 조건 모두 검증
- **구문 검증 (Statement Coverage)**: 모든 실행문 검증

## 📌 소스코드 최적화

**가단의중추**

- 가독성 향상
- 단순 구조
- 의존 최소화
- 중복 제거
- 추상화로 재사용성

  

## ISO/IEC 9126

소프트웨어 품질 특성

**기신사효유이**

- 기능성
- 신뢰성
- 사용성
- 효율성
- 유지보수성
- 이식성

  

## ISO/IEC 14598

소프트웨어 품질 평가 기준

**반재공객**

- 반복성
- 재현성
- 공정성
- 객관성

## DAC vs MAC 비교

|항목|DAC|MAC|
|---|---|---|
|권한 결정 주체|리소스 소유자|시스템 정책|
|유연성|높음|낮음|
|보안성|낮음|높음|
|대표 기술|`chmod`, `chown`|SELinux, AppArmor|
|실사용 예시|일반 리눅스 시스템|정부, 군 시스템|

## 📌 **<span style="color: blue">UML 관계</span>**

**연집합합의일실**

UML에서 클래스 간의 관계를 표현하는 방법들

1. **연관 관계 (Association)**
   - 클래스 간의 일반적인 연결
   - 양방향 또는 단방향 가능
   ```mermaid
   classDiagram
       class Student {
           -name: String
       }
       class Course {
           -title: String
       }
       Student "1" -- "0..*" Course : 수강
   ```

2. **집합 관계 (Aggregation)**
   - 전체-부분 관계 (약한 포함)
   - 부분이 전체 없이도 존재 가능
   ```mermaid
   classDiagram
       class Department {
           -name: String
       }
       class Professor {
           -name: String
       }
       Department "1" o-- "0..*" Professor : 소속
   ``` 

3. **합성 관계 (Composition)**
   - 강한 포함 관계
   - 부분이 전체 없이는 존재 불가
   ```mermaid
   classDiagram
       class Car {
           -model: String
       }
       class Engine {
           -type: String
       }
       Car "1" *-- "1" Engine : 포함
   ```

4. **의존 관계 (Dependency)**
   - 일시적인 관계
   - 한 클래스가 다른 클래스를 파라미터로 사용
   ```mermaid
   classDiagram
       class Order {
           +calculateTotal()
       }
       class Calculator {
           +calculate()
       }
       Order ..> Calculator : 사용
   ```

5. **일반화 관계 (Generalization)**
   - 상속 관계
   - 부모-자식 클래스 관계
   ```mermaid
   classDiagram
       class Animal {
           +eat()
       }
       class Dog {
           +bark()
       }
       Animal <|-- Dog
   ```

6. **실체화 관계 (Realization)**
   - 인터페이스 구현
   - 클래스가 인터페이스를 구현
   ```mermaid
   classDiagram
       class Flyable {
           <<interface>>
           +fly()
       }
       class Bird {
           +fly()
       }
       Flyable <|.. Bird : implements
   ```

### 📌 UML 관계 표기법 외우는 팁

1. **실선 vs 점선**
   - **실선**: 영구적/강한 관계 (항상 존재하는 관계)
   - **점선**: 일시적/약한 관계 (임시로 사용하는 관계)

2. **화살표 유무**
   - **화살표 없음**: 양방향 관계
   - **화살표 있음**: 단방향 관계

3. **채워진 vs 빈 마름모**
   - **채워진 마름모 (◆)**: 합성 관계 (Composition) - "죽음의 관계" (전체가 없어지면 부분도 없어짐)
   - **빈 마름모 (◇)**: 집합 관계 (Aggregation) - "생존의 관계" (전체가 없어져도 부분은 살아남음)

4. **쉬운 연상법**
   - **합성** (◆): "죽음의 관계" - 마름모가 채워져 있어서 "죽음"을 상징
   - **집합** (◇): "생존의 관계" - 마름모가 비어있어서 "생존"을 상징
   - **의존** (..>): 점선 + 화살표 - "임시로 사용"하는 느낌
   - **일반화** (<|--): 실선 + 삼각형 - "상속"의 강한 관계
   - **실체화** (<|..): 점선 + 삼각형 - "인터페이스 구현"의 약한 관계
   - "실선은 영구, 점선은 임시"
   - "채워진 마름모는 죽음, 빈 마름모는 생존"
   - "화살표는 방향, 삼각형은 상속"

