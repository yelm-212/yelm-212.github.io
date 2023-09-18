---
title:  "[Java] Collection Framework"
excerpt: "Java의 컬렉션 프레임워크를 학습한 내용을 정리했습니다."

tags:
  - [Blog, CS]

toc: true
toc_sticky: true
 
date: 2023-03-24
last_modified_at: 2023-03-24
---
# Java `hashcode()` &`equals()`
- 자바의 모든 객체는 `Object` 클래스에 정의된 두 함수를 상속받는다.
- `equals()` : 
	- 2개의 객체가 동일한지 (참조하는 것이 동일한지 여부를 확인함
	- `String`과 같이 서로 다른 메모리에 할당되나 같은 값을 가지는 경우, 이 메소드를 오버라이딩해 같은 값을 가지는지 여부를 확인하도록 처리가 가능하다.
-  `hashcode()` 
	- 런타임에 객체의 유일한 정수 값을 반환한다.
	- `hashtable`등과 같은 자료구조를 사용할때 데이터가 저장되는 위치를 결정하기 위해 사용된다.
- 두 객체가 `equals()`에 의해 동일하다면, 두 객체의` hashCode() `값도 일치
- 두 객체가 `equals()`에 의해 동일하지 않다면, 두 객체의 `hashCode()` 값은 일치하지 않아도 됨
- 두 메소드는 함께 오버라이딩되어야 한다.
  
# Thread Safe & Syncronized
- 멀티스레드 환경에서 동기화를 하지 않으면, 데이터의 안정성과 신뢰성을 보장할 수 없다.
- 자바에서는 `synchronized` 키워드를 사용해 스레드간 동기화를 시켜 `thread-safe`를 성립시킨다.

```
@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
```
- 너무 자주 쓰면 프로그램 성능 저하가 일어날수도 있음 (적절한 상황에 맞추어 사용하기)