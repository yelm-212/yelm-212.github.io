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
- 웹 요청에 대한 동적인 처리를 해줌
	- 자바로 구현된 CGI에서 사용자 입력을 받아 동적으로 HTML 생성

# filter

- 요청과 응답을 가로채서 처리하는 기능을 제공
- 클라이언트의 요청이 컨트롤러에 도달하기 전에 사전 처리 작업을 수행하거나, 컨트롤러가 응답을 반환하기 전에 사후 처리 작업을 수행하는 등의 역할을 수행할 수 있다.

# servlet container

- 서블릿을 관리해주는 컨테이너.
- 대표적으로 Tomcat이 있다.

## Container의 역할

1. 웹 서버와의 통신 지원
	- 서블릿과 웹 서버가 쉽게 통신할수 있게 해줌
	- 소켓 기능을 API로 제공해 복잡한 과정을 생략하고, 구현해야 할 비즈니스 로직에만 집중
2. 서블릿 생명주기 관리
	- 서블릿 클래스의 초기화 메소드, 인스턴스화, 요청에 대한 메소드 호출
3. 멀티스레드 지원 및 관리
	- 요청이 올 때마다 새로운 자바 Thread를 하나 생성 & 실행 후 자동 제거
4. 선언적인 보안 관리
	- 보안 관리는 XML 배포 서술자에. 
	- 보안 문제로 자바 소스 수정할 필요 없음 

# tomcat

![](/attatchments/Pasted image 20230501161438.png)

- spring에서 작성한 웹 프로그램을 Apache tomcat을 이용해 웹 서비스로 사용할 수 있다.
- Tomcat이 WAS 역할을 수행한다.

# 동작과정

![](/attatchments/Pasted image 20230501155752.png)

1. 사용자가 URL을 클릭하면 HTTP Request를 Servlet Container에 보낸다.
2. Servlet Container는 `HttpServletRequest`, `HttpServletResponse` 두 객체를 생성한다.
3. 사용자가 요청한 URL을 분석하여 어느 서블릿에 대한 요청인지 찾는다. (DD를 참조하여 분석)
4. 컨테이너는 서블릿 service() 메소드를 호출하며, `POST` / `GET` 요청에 따라 `doGet()` 또는 `doPost()`가 호출된다.
5. `doGet()` or `doPost()` 메소드는 동적인 페이지를 생성한 후 `HttpServletResponse`객체에 응답을 보낸다.
6. 응답이 완료되면 `HttpServletRequest`, `HttpServletResponse` 두 객체를 소멸시킨다.