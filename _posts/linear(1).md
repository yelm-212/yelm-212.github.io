---
title:  "[Java] CS - 선형 자료구조"
excerpt: "Array, List, Hashtable, Queue, Stack"

categories:
  - Data-Structure
tags:
  - [Blog, CS, data-structure, Array, List, Hashtable, Queue, Stack]

toc: true
toc_sticky: true
 
date: 2023-03-16
last_modified_at: 2023-03-16

---

다음의 자료구조는 모두 데이터가 일렬로 연결된 형태를 가지고 있다.
# Array
- 연속적인 메모리 영역에 저장되는 원소들의 집합
- 선언 시에 배열의 사이즈가 고정된다.
	- 기억 장소의 추가가 어려움.
- random access 가 가능하므로, 검색이 빠르지만 할당된 크기에서 벗어난 삭제 혹은 삽입 연산이 불가능하다.

# List
- 크기가 정해져 있지 않으며, 사이즈가 동적이다.
- 자료의 위치가 연속적이지 않다.
- JAVA에는 [ArrayList와 LinkedList](obsidian://open?vault=cs-study&file=yelm-212%2FJAVA%2FString%2C%20List%2C%20Map#List)가 존재한다.

## ArrayList vs LinkedList
| 클래스 | 데이터 구조 | 임의 접근 | 삽입/삭제 성능 |
|-------|----------------|---------------|------------------------------|
| ArrayList | 배열 | 빠름 | 느림 |
| LinkedList | 이중 연결 리스트 | 느림 | 빠름 |

| 클래스 | 끝에 추가 | 중간/처음에 추가 | 끝에서 삭제 | 중간/처음에서 삭제 |
|-------|---------------|-----------------|--------------------|--------------------------------|
| ArrayList | 빠름 | 느림 | 빠름 | 느림 |
| LinkedList | 느림 | 빠름 | 느림 | 빠름 |


# HashTable
![](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Hash_table_3_1_1_0_1_0_0_SP.svg/1024px-Hash_table_3_1_1_0_1_0_0_SP.svg.png)
- Hashing : 키에 hash function을 적용해 테이블의 주소를 계산해 원소에 접근하는 방식
-  hash function : 키를 입력으로 받아 Hash address를 생성하고, 이 주소를 hash table의 인덱스로 사용하게 한다.
- `HashTable` : 키에 대한 연산에 의해 직접 접근이 가능한 구조.
	- Java에서 Map 인터페이스의 레거시 구현체로 `Key`와 `Value`의 한 쌍으로 이루어지는 데이터의 집합이다.
	- key 혹은 value 값으로 `null` 불가능
	- 중복 key 사용 불가능
	- 순서 보장되지 않음

> - Stack, Queue 두 자료구조는 자료를 특정 순서대로 저장 및 핸들링이 가능하게 하는 자료구조이다.
> - 이 두가지 자료구조의 주된 차이점은 자료의 추가와 삭제 순서이다.


# Queue
- First In First Out : 먼저 들어간 데이터를 먼저 꺼낸다.
- 예시 : 인쇄 작업 대기 목록, 버퍼
```
Queue<Runnable> tasks = new LinkedList<>();

// Enqueue tasks
tasks.add(() -> System.out.println("Task 1"));
tasks.add(() -> System.out.println("Task 2"));
tasks.add(() -> System.out.println("Task 3"));

// Dequeue and execute tasks
while (!tasks.isEmpty()) {
    Runnable task = tasks.remove();
    task.run();
}

```


# Stack
- Last In First Out : 마지막에 저장한 데이터를 가장 먼저 꺼낸다. 
- 예시 : undo / redo 기능
```
String str = "Hello World";
Stack<Character> stack = new Stack<>();

// Push each character of the string onto the stack
for (char c : str.toCharArray()) {
    stack.push(c);
}

// Pop each character from the stack to create the reversed string
String reversedStr = "";
while (!stack.isEmpty()) {
    reversedStr += stack.pop();
}

System.out.println(reversedStr); // Output: dlroW olleH

```