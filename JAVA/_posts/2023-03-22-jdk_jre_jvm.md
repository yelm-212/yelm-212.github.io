---
title:  "[Java] JDK, JRE, JRM"
excerpt: "JDK, JRE, JRM에 대해 학습한 내용을 정리하였습니다."

tags:
  - [Blog, CS, Java, JDK, JRE, JVM]

toc: true
toc_sticky: true
 
date: 2023-03-22
last_modified_at: 2023-03-22

---

#  JDK, JRE, JVM의 정의

![Pasted image 20230227214942.png](https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png)
[](https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png)
1. **JDK** (Java Development Kit)
	- JDK는 Java 프로그램을 개발 및 실행하기 위한 환경을 제공하는 일종의 키트와 같다.
	- JDK는 개발 도구와 JRE를 포함한다.
2. **JRE** (Java Runtime Environment)
	- 자바를 컴퓨터에서 실행하는 환경만을 제공
	- JRE는 end-user에게만 사용된다.
3. **JVM** (**Java Virtual Machine**)
	- JRE과 JDK 모두에 내장되어 있다.
	- 자바 프로그램을 한 줄마다 실행하는 기능을 한다 -> 인터프리터 
	- UI toolkit과 통합 라이브러리, 언어와 유틸리티 라이브러리, 기타 라이브러리, JVM, 배포 기술 등을 구성 요소로 가진다.
	- JVM은 자바 프로그램을 실행할때 JRE의 인스턴스가 된다. (runtime interpreter)

## JAVA의 컴파일(compile)과 실행(execution) 과정

자바는 플랫폼에 독립적인 프로그래밍 언어로, 2단계의 컴파일 과정을 거친다.
- 컴파일 단계 : OS에 독립적인 컴파일러를 사용한다.
	- 자바 컴파일러가 자바 class(.java)들을 바이트코드 파일(.class)로 변환한다.
	- 바이트코드를 실행하기 위해, OS에 상응하는 JVM이 필요하다.
- 실행 단계 : 운영체제에 알맞게 갖추어진 JVM을 사용한다.
	- 클래스 로더 : 바이트코드를 받아들이고 힙 메모리에 자바 클래스를 올린다.
		- Load, Linking, Initializing의 3단계를 거친다.
		- Load
		- Linking
		- Initializing
	- 바이트코드 검증 : 클래스 로더가 컴퓨터에 위해를 주거나 문제를 일으키지 않을지 검증한다. 만일 검증시 오류가 있으면, Exception을 발생시키고 컴파일을 중지한다.
	- JIT 컴파일러 : 바이트코드를 기계어로 번역해 실행시킨다.

##  컴파일 언어 vs 인터프리터 언어

Java는 컴파일 언어의 특성과 인터프리터 언어의 특성을 모두 가지고 있다.

| 특성 | 컴파일 언어 | 인터프리터 언어 |
|---------|------------------|----------------------|
| 실행 과정 | 코드가 바로 기계어로 번역됨 | 코드를 한줄씩 읽어와 번역함 |
| 속도 | 빠른 실행 속도 | 느린 실행 속도 |
| 디버깅 | 디버깅이 어렵다 | 디버깅이 쉽다 |
| 이식성 | 기계어로 옯기는 과정이 이식성을 낮춘다. | 다양한 플랫폼에 이식이 가능하다.  |
| 메모리 | 코드가 미리 컴파일되어 있어 메모리 효율이 좋다(높다) | 코드가 번역되는 과정이 메모리 효율을 상대적으로 나쁘게(낮게) 한다 |
| 예시 언어 | C, C++, Java | Python, Ruby, JavaScript |

- 자바의 컴파일 과정에서 살펴보았듯, 자바는 컴파일과 인터프리터 특성을 혼합하여 사용하고 있다. 
	- 자바로 쓰여진 코드를 바이트코드로 컴파일 후 실행 시 JVM에서 interprete

# JAVA의 메모리 관리

컴퓨터에서 메모리는 중요한 자원이고, 여러 프로그래밍 언어에서 메모리 관리를 위해 다양한 정책을 가지고 있다. 
JAVA에서는 개발자가 직접적으로 메모리 할당을 하지 않고, JVM과 Garbage collector가 메모리 할당과 해제의 역할을 가진다. 이와 같은 방법의 이점은, C와 같은 언어의 방식과 같이 메모리의 할당에 대해 개발자가 크게 신경을 쓰지 않아도 된다는 것이다. 

- JVM은 프로그램의 실행시 데이터 영역을 정의한다. 
- JVM의 일부 영역은 프로그램에서 사용되는 스레드에 의해 생성되고, 메모리 영역은 JVM이 사라질 때에 같이 사라진다. 데이터 영역의 스레드는 인스턴스화 시점에 생성되고, 스레드가 사라질 때 (exit) 사라진다. 
- 이와 같은 메모리 영역들을 
	- *Heap area, Method area* - 모든 스레드가 공유해 사용하는 GC 대상- , 
	- *JVM stack, Native method stack, PC register* - 스레드마다 하나씩 생성됨-
	- 로 구분할 수 있다.

##  JVM 메모리 구조

![JVM Memory area parts](https://media.geeksforgeeks.org/wp-content/uploads/Memory.png)[](https://media.geeksforgeeks.org/wp-content/uploads/Memory.png)

- Heap area 
	- 모든 생성된 객체 및 배열이 저장되는 영역
	- 동적 메모리 할당을 지원한다.
- Method area
	- 필드와 메서드 정보, 데이터 타입 정보, Static 변수 등이 저장되는 영역
- JVM stack
	- 지역 변수, 파라미터, 반환 값, 연산에 사용되는 임시 값 등이 저장되는 영역.
	- 주로 생애 주기가 짧은 데이터들이 저장된다.
- Native method stack 
	- Java로 쓰여있지 않음. 다른 언어로 작성된 네이티브 코드를 실행할 때 사용되는 메모리 영역으로 C 스택을 사용한다.
- PC register
	- 스레드 생성시마다 생성된다.
	- (몇몇 특정한 플랫폼에서) 반환되는 위치의 주소나 명령을 저장힌디.

##  Garbage Collector 동작과정

### Automatic garbage collection

간단하고 직관적이지만 큰 힙에서 성능이 떨어짐
- 사용하지 않는 메모리를 자동으로 회수해준다.
- Heap 메모리 영역에 생성된 객체들 중 참조죄지 않은 객체를 탐색 후 제거하는 역할
1. 마킹 : 사용중인 메모리와 사용중이지 않은 메모리를 구분하여 마킹한다.
![Pasted image 20230227170822.png](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide3.png)
- 참조되는 객체들은 파란색으로, 참조되지 않는 객체들은 주황색으로 표시되었다.
2. 일반 삭제 : 참조되지 않은 객체를 제거하고 참조된 객체와 포인터를 여유 공간에 남긴다.
![Pasted image 20230227171325.png](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide1b.png)
3. 압축 삭제 : 성능을 더욱 향상시키기 위해 나머지 참조 객체를 압축할 수 있다.
![Pasted image 20230227171721.png](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide4.png)
- 참조된 객체를 빈 공간의 가장 앞으로 이동시킴으로써, 새로운 메모리 할당을 훨씬 쉽고 빠르게 만든다.

### Generational garbage collection

![Pasted image 20230227173019.png](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide5.png)
객체의 수명에 따라 힙을 여러 개의 세대로 나누어서 사용한다. 
- 새로운 객체는 Young Generation에 할당되고, 일정 주기마다 Young Generation을 스캔하여 사용되지 않는 객체를 식별한다. (minor garbage collection) 
- 객체가 여러 번 스캔에 걸쳐 살아남으면 오래된 세대(old generation)로 이동한다.
- Old gen.은 오래 살아남은 객체를 저장하는 데 사용된다. 일반적으로, young gen.의 객체에 대한 임계값이 설정되고 그 값이 충족되면, 그 물체는 다음 세대로 옮겨진다. 즉 Old gen.이 수집된다. (major garbage collection = Stop the World events)
- Major garbage collection은 모든 객체를 포함하므로, 훨씬 느리다. 그러므로 주요 어플리케이션에서는 이를 최소화해야 한다.
- Permanent generation에는 어플리케이션에서 사용되는 클래스와 메소드를 설명하는 데 JVM이 요구하는 메타데이터가 포함되어 있다. 
	- 어플리케이션에서 사용하는 클래스에 따라 런타임에 JVM으로 채워진다. 
	- Java SE 라이브러리 클래스와 메소드는 여기에 저장될 수 있다.
- JVM이 더 이상 필요하지 않고 다른 클래스에 공간이 필요할 수 있다고 판단되면 클래스가 수집(unload)될 수 있다. 
- Permanent generation는 full garbage collection에 포함되어 있다.

##  Java8의 큰 특징 + Java11과의 차이점

|  | Java 8 | Java 11 |
|---------|--------|---------|
| Release Date | 2014 | 2018 |
| LTS | Yes | Yes (next after Java 8) |
| 새로운 기능 | -  |  성능과 보안 기능 향상, HTTP client API, lambda 변수에 대한 지역 변수 문법, Unicode 10.0 지원, Flight Recorder, ZGC garbage collector |
| 공식 업데이트 마감일 | Jan. 2019 | 업데이트 |
| 이식성 | 이전의 Java 어플리케이션들과 호환 가능 | deprecated APIs and features in Java 9 and 10 -> 이전의 Java 어플리케이션들과 호환 불가 |


### 문서 출처
- [Differences between JDK, JRE and JVM](https://www.geeksforgeeks.org/differences-jdk-jre-jvm/)
- [How Java Code Compile And Run ?](https://medium.com/javarevisited/how-java-code-compiled-and-run-e4702fb83ffa)
- [Compilation and Execution of a Java Program](https://www.geeksforgeeks.org/compilation-execution-java-program/)
- [Compiled vs. interpreted langs](https://www.ibm.com/docs/en/zos-basic-skills?topic=zos-compiled-versus-interpreted-languages)
- Gosling, J., Joy, B., Steele, G., & Bracha, G. (2005). The Java Language Specification (Third Edition). Addison-Wesley Professional.
- [Java Memory Management](https://www.geeksforgeeks.org/java-memory-management/)
- [Java Garbage Collection Basics](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html)
- [Where Does Java’s String Constant Pool Live, the Heap or the Stack?](https://www.baeldung.com/java-string-constant-pool-heap-stack)
- [Guide to Java String Pool](https://www.baeldung.com/java-string-pool)
- [Why String Is Immutable in Java?](https://www.baeldung.com/java-string-immutable)