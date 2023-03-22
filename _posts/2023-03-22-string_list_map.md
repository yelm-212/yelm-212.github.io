---
title:  "[Java] String, List, Map"
excerpt: "String, List, Map에 대해 학습한 내용을 정리하였습니다."

categories:
  - Java
tags:
  - [Blog, CS, Java, string, list, map]

toc: true
toc_sticky: true
 
date: 2023-03-22
last_modified_at: 2023-03-22

---

# String
## String vs StringBuffer vs StringBuilder 

| 클래스 | 가변성 | Thread Safety | 사용 사례 |
|-------|------------|--------------|----------|
| String | 불변 | Thread-safe  | 불변 문자 시퀀스를 다룰 때 |
| StringBuffer | 가변 | Thread-safe | 멀티스레드 환경에서 안전하게 수정할 수 있는 가변 문자 시퀀스를 다룰 때 |
| StringBuilder | 가변 | Thread-safe 아님| 싱글스레드 환경에서 가변 문자 시퀀스를 다룰 때 |

## Immutable Object
- 생성 후 상태를 바꿀 수 없는 객체
##  `String a = ""` vs `String a = new String("")`
[](https://journaldev.nyc3.digitaloceanspaces.com/2012/11/String-Pool-Java1-450x249.png)
- `String a = new String("")` : 새로운 객체를 만드는 것으로 힙 영역에 저장된다.
- `String a = ""`  : 리터럴을 사용한 변수 할당으로 `string constant pool` 영역에 저장
```
s1 == s2 // false
s1.equals(s2)  // true
```
# List
Array과 유사하나, 리스트는 크기가 정해져 있지 않고 사이즈가 동적으로 변하는 배열
## ArrayList vs LinkedList
| 클래스 | 데이터 구조 | 임의 접근 | 삽입/삭제 성능 |
|-------|----------------|---------------|------------------------------|
| ArrayList | 배열 | 빠름 | 느림 |
| LinkedList | 이중 연결 리스트 | 느림 | 빠름 |

| 클래스 | 끝에 추가 | 중간/처음에 추가 | 끝에서 삭제 | 중간/처음에서 삭제 |
|-------|---------------|-----------------|--------------------|--------------------------------|
| ArrayList | 빠름 | 느림 | 빠름 | 느림 |
| LinkedList | 느림 | 빠름 | 느림 | 빠름 |

# Map
- Key와 Value의 한 쌍으로 이루어지는 데이터의 집합
- 순서가 보장되지 않음
- key 중복 비허용
- 뛰어난 검색 속도를 가짐
- 인덱스가 따로 존재하지 않고, `iterator`를 사용
## HashTable vs HashMap vs LinkedHashMap vs TreeMap
![](https://www.programcreek.com/wp-content/uploads/2009/02/MapClassHierarchy-600x354.jpg?ezimgfmt=rs%3Adevice%2Frscb13-1)
- `HashTable` 
	- Java에서 Map 인터페이스의 레거시 구현체
	- key 혹은 value 값으로 null 불가능
	- 멀티스레드 환경에서 안전하며 동기화되어 있으나, 단일 스레드 환경에서 성능에 영향을 줄 수 있음
	- 순서 보장되지 않음
- `HashMap` 
	- 동기화되지 않는 맵 인터페이스의 구현체
	- 단일 스레드 환경에서 `Hashtable`보다 빠름
	- key 혹은 value 값으로 null 가능
	- `get()`,` put()`과 같은 기본 메소드의 성능이 O(n)
	- 순서 보장되지 않음
- `LinkedHashMap`
	- 값의 입력된 순서를 유지하는 `HashMap`
- `TreeMap`
	- 정렬된 맵 인터페이스의 구현체
	- `get()`,` put()`과 같은 기본 메소드의 성능이 O(log n)
	- value 값으로 null 가능
	- 반복 순서는 키의 자연 순서에 따라 결정된다.
- 동기화가 필요한 경우 `Hashtable`을 사용하고 동기화가 필요하지 않고 빠른 성능이 필요한 경우` HashMap`을 사용
- 삽입 순서를 유지해야하는 경우 `LinkedHashMap`을 사용하고 정렬된 맵이 필요한 경우 `TreeMap`을 사용
## HashMap vs ConcurrentHashMap
두가지 모두 `Map interface`의 구현체이나, 상황에 따라 구분하여 사용하여야 한다.
- `HashMap`
	- Thread-safe 하지 않은 구현체로, 멀티스레드 환경에서는 외부에서 동기화해줄 필요가 있다.
- `ConcurrentHashMap`
	- Thread-safe 하지 않은 구현체
	- 외부 동기화 없이 동시 엑세스를 허용하는 동시에 모든 Map 작업이 원자적임을(atomic) 보장한다.
	- 세밀한 locking을 지원 (멀티스레드 성능 향상)
- `HashMap`은 단일 스레드 환경이나 외부 동기화가 가능한 경우에 적합
- `ConcurrentHashMap`은 맵에 대한 동시 액세스가 필요한 멀티 스레드 환경에 적합