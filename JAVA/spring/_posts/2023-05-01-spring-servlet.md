---
title:  "[Java Spring] Servlet"
excerpt: "Servlet에 대해 학습한 내용을 정리하였습니다."

tags:
  - [Blog, CS]

toc: true
toc_sticky: true
 
date: 2023-05-01
last_modified_at: 2023-05-01

---

# 개념

- Java EE에 제공하는 Web Application 제작용 API
- 웹 **요청에 대한 동적인 처리**를 해줌
	- 자바로 구현된 CGI에서 사용자 입력을 받아 동적으로 HTML 생성
- Java 스레드를 이용해 동작한다.
- MVC 패턴에서의 컨트롤러로 이용된다

# filter

- 클라이언트의 요청이 컨트롤러에 도달하기 전에 사전 처리 작업을 수행하거나, 컨트롤러가 응답을 반환하기 전에 사후 처리 작업을 수행하는 등의 역할을 수행할 수 있다.

# servlet container

- http 요청을 받아 servlet을 실행시키고 생명주기를 관리하는 컴포넌트
- 추상 클래스 `HttpServlet`을 구현(`extend`) 해서 만들어진다.
- 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라 한다.
	- Tomcat, jetty, jboss 등...

## Container의 역할

1. 웹 서버와의 통신(API) 지원
	- 서블릿과 웹 서버가 쉽게 통신할수 있게 해줌
	- 소켓 기능을 API로 제공해 복잡한 과정을 생략하고, 구현해야 할 비즈니스 로직에만 집중
2. 서블릿 생명주기 관리
	- 서블릿 클래스의 초기화 메소드, 인스턴스화, 요청에 대한 메소드 호출
3. 멀티스레드 지원 및 관리
	- 처음에 일정한 수의 쓰레드를 미리 생성해놓고, 클라이언트의 요청이 오면 쓰레드 풀 안에 있는 쓰레드를 할당한다.
	- 요청을 처리하고 나면 해당 쓰레드를 다시 반납한다.
4. 선언적인 보안 관리
	- 보안 관리는 XML 배포 서술자에. 
	- 보안 문제로 자바 소스 수정할 필요 없음 

# Apache Tomcat

![](/attatchments/Pasted image 20230501161438.png)

- Tomcat은 [WEB/WAS](https://yelm-212.github.io/computer_science/network/net03/#web-server-vs-wasweb-application-server) 기능을 가진 전 세계적으로 가장 많이 사용되는 오픈 소스 웹 컨테이너이다.
- Spring Boot에는 Tomcat이 기본 WAS로 내장되어 있다.

# 동작과정

![](/attatchments/Pasted image 20230501155752.png)

1. 사용자가 URL을 클릭하면 HTTP Request를 Servlet Container에 보낸다.
2. Servlet Container는 `HttpServletRequest`, `HttpServletResponse` 두 객체를 생성한다.
3. 사용자가 요청한 URL을 분석하여 **어느 서블릿에 대한 요청**인지 찾는다. (배포서술자(DD, Deplyment Descriptor)를 참조하여 분석)
4. 서블릿 컨테이너에서 실행된 적 or 메모리에 생성된 인스턴스(process)가 있는지 체크한다,
    - 처음 실행 : 인스턴스 생성 후 `init()` 호출 -> 초기화 후 스레드 할당
    - 이미 실행 : 기존 인스턴스에 스레드 할당
5. 컨테이너는 해당 서블릿에서 `service()` 메서드를 호출한후 HTTP 요청 메서드에 따라 `doGet()` 또는 `doPost()`를 호출
    - 이 과정에서 request와 response 객체가 제공된다.
6. `doGet()` or `doPost()` 메소드는 동적인 페이지를 생성한 후 `HttpServletResponse`객체에 응답을 보낸다.
7. 응답이 끝나면 `HttpServletRequest`, `HttpServletResponse` 두 객체를 소멸시킨다.