---
title:  "[Java] 자바 기초 "
excerpt: "자바의 기초적인 내용에 대해 정리하였습니다."

categories:
  - Java
tags:
  - [Blog, CS, 접근제어자, class, object, instance, overloading, overriding, primitive-type, reference-type, wrapper-class, abstract-class, interface, static, final, generic, stream]

toc: true
toc_sticky: true
 
date: 2023-03-22
last_modified_at: 2023-03-22

---

#   접근제어자

변수나 메서드의 사용 권한에 대해 접근 제어자를 사용하여 설정할수 있다.
`private -> default -> protected -> public` 순으로 보다 많은 접근을 허용한다.
-   **`public` 접근 제한자:**  자유롭게 사용 
-   **`protected` 접근 제한자:** 같은 패키지 또는 자식 클래스
-   **`private` 접근 제한자:** 외부에서 사용될 수 없도록 함
위 세 가지 접근 제한자를 별도로 설정하지 않으면, `default` 접근 제한을 가지며 해당 패키지 내에서만 접근이 가능하다.
#   클래스, 객체, 인스턴스 차이
- 클래스 
	- 객체를 만들어내기 위한 템플릿 혹은 청사진.
	- 클래스의 객체가 소유할 일련의 속성과 행동을 정의한다.
- 객체
	- 클래스를 이용해 만들어진 클래스의 인스턴스
	- 클래스로부터 정의된 속성을 가지고,클래스로부터 정의된 메소드를 사용할 수 있다
- 인스턴스
	- 메모리 상에 있는 하나의 객체를 의미함


#   Overloading vs Overriding

|         | Overloading | Overriding |
|---------|-----------------|-----------------|
| 파라미터   | 파라미터는 상이하나 이름이 동일한 여러개의 메소드를 가진다.| 하위 클래스의 메소드는 상위 클래스의 메소드와 동일한 파라미터 리스트를 가진다. |
| 반환값   | 다양한 반환 값을 가질 수 있다. | 상위 클래스와 동일한 리턴 타입을 가진다.|
| Method resolution  | 메소드가 호출될 때에 넘겨지는 전달인자의 수, 타입, 순서에 따라 컴파일 할 때 결정된다.   | 참조되는 객체의 실제 타입에 의해 결정된다.|
| 상속   | 상속으로 연관되지 않은 타 클래스 혹은 동일 클래스에서 일어날 수 있다.| 상속에 의해 연관 관계가 생기는 하위 클래스에서만 일어날 수 있다.|

#   Primitive type vs Reference type
| Aspect      | Primitive Type                 | Reference Type                                        |
| ----------- | ------------------------------ | ----------------------------------------------------- |
| 정의        | 언어 자체에 내장된 데이터 타입 | 프로그래머 혹은 언어에 의해 정의된 복잡한 데이터 타입 |
| 메모리 할당 | 스택                           | 힙                                                    |
| Pass by     | 값 (Value)                     | 주소 (Reference).                                     | 
| Null value  | null값 불가                    | null값 가능                                          |

##  Call by Reference vs Call by Value

| Aspect | Call by Value                                            | Call by Reference                                           |
| ------ | -------------------------------------------------------- | ----------------------------------------------------------- |
| 정의   | 메소드 또는 함수에 실제 값의 사본을 넘긴다               | 실제 값의 참조 값(주소)이 전달된다.                         |
| 메모리 | 값의 사본이 만들어지기 때문에 더 많은 메모리를 필요로 함 | 값의 사본을 만들 필요가 없으므로 메모리를 절약함            |
| 속도   | (상대적으로) 느림                                        | (상대적으로) 빠름                                           |
| 부작용 | 원본 값이 바뀌지 않으므로 부작용 없음                    | 함수/메소드 내의 원본 값이 변경되어 부작용을 유발할 수 있음 |

##   Wrapper Class
-  기본 타입에 해당하는 데이터를 객체로 포장해 주는 클래스
- 각각의 타입에 해당하는 데이터를 인수로 전달받아, 해당 값을 가지는 객체로 만들어줌

![](http://www.tcpschool.com/lectures/img_java_boxing_unboxing.png)
- Boxing : 기본 타입 데이터를 Wrapper 클래스의 인스턴스로 변환하는 과정
- Unboxing : Wrapper 클래스의 인스턴스에 저장된 값을 기본 타입 데이터로 꺼내는 과정
- 자바에서는 boxing과 unboxing이 필요한 상황에서 컴바일러가 자동으로 이를 처리해준다. (AutoBoxing / Unboxing)

```

Java program to demonstrate Autoboxing & AutoUnboxing

import java.util.ArrayList;
class Autoboxing
{
	public static void main(String[] args)
	{
		char ch = 'a';

		// Autoboxing- primitive to Character object conversion
		Character a = ch;

		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		// Autoboxing because ArrayList stores only objects
		arrayList.add(25);

		// printing the values from object
		System.out.println(arrayList.get(0));
	     
	    // objects to data types (retrieving data types from objects)
        // unwrapping objects to primitive data types
		char newch = a;
		System.out.println(newch);
	}
}
```

# interface vs abstract class 
- ![](https://media.geeksforgeeks.org/wp-content/uploads/Abstract-Class-vs-Interface.png)

| 추상 클래스 | 인터페이스 |
| --- | --- |
| 인스턴스화 할 수 없음 | 인스턴스화 할 수 없음 |
| 다른 클래스의 기본 클래스로 사용 | 어떤 클래스에서든 구현 가능한 추상 메서드 모음 |
| 추상 및 구상 메서드 모두 포함 가능 | 추상 메서드만 포함 가능 |
| 하위 클래스에서 추상 메서드를 구현해야 함 | 인터페이스를 구현하는 클래스는 모든 메서드에 대한 구현을 제공해야 함 |
| 인스턴스 변수와 생성자를 가질 수 있음 | 인스턴스 변수나 생성자를 가질 수 없음 |
| 메서드에 대한 기본 구현을 제공할 수 있음 | 메서드 시그니처만 포함 가능 |
| 하나의 추상 클래스만 상속 가능 | 여러 인터페이스를 구현 가능 |


## 추상 클래스를 사용하는 경우
1.  Java 애플리케이션에서 여러개의 클래스가 일부 코드를 공유함
2.  추상 클래스 내에 `non-static` 또는 `non-final` 필드를 정의하여 해당 필드가 속한 객체의 상태에 접근하고 수정할 수 있는 메서드를 통해 객체 상태를 변경함
3.  확장하는 클래스가 많은 공통 메서드 또는 필드를 가지거나 `public` 이외의 접근 제어자 (`protected`,`private`)에 대한 접근 권한이 필요한 경우.

## 인터페이스를 사용하는 경우
1.  total abstraction : 인터페이스 내에서 선언된 모든 메서드는 해당 인터페이스를 구현하는 클래스에서 구현함
2.  multiple inheritance : 클래스에서 하나 이상의 인터페이스를 구현함.
3.  특정 데이터 유형의 동작을 지정하고 해당 동작을 구현하는 클래스는 중요하지 않은 경우.

#  Checked Exception vs UnChecked Exception
  ![](https://user-images.githubusercontent.com/45676906/105691109-2cda9400-5f40-11eb-9003-a14873c2eaf2.png)

| Checked Exceptions                                              | Unchecked Exceptions                                               |
| --------------------------------------------------------------- | ------------------------------------------------------------------ |
| 메서드 시그니처에서 선언하거나 `try-catch` 블록으로 처리해야 함 | 메서드 시그니처에서 선언하지 않고 처리하지 않아도 됨               |
| `IOException`, `SQLException` 등이 예시                         | `NullPointerException`, `ArrayIndexOutOfBoundsException` 등이 예시 |
| 복구 가능한 오류에서 발생                                       | 프로그래밍 상 오류에서 발생                                        |
| 컴파일러가 `catch` 또는 `throws`로 처리 여부를 검증함           | 컴파일러가 처리 여부를 검증하지 않음                               |
| `Exception` 클래스에서 파생됨                                   | `RuntimeException` 클래스에서 파생됨                               |
| 발생시 롤백 안해도 됨                                                                | 예외 발생시 롤백함                                                                    |


# static
- 자바에서 변수 혹은 메서드를 static으로 선언하면 이는 특정 인스턴스에 속하기 보다는 클래스에 속하게 된다. (모든 객체가 이를 공유한다.)
- 힙 영역이 아닌 static 영역에 할당된다.

# final
- 메소드에서, 더이상 하위 클래스에 `override`되지 않음을 나타낸다.
- 어떠한 속성을 한번만 할당이 가능하게 한다.
# generic
- 아무거나 들어갈 수 있게 할려면 object 타입 쓰면 되는데 왜 제너릭 씀? -> 그러면 타입 검사, 변환시 오류 뜰수도 있으니까 코드가 길어짐. 이걸 간결하게 가능하게 하는게 제너릭
- 컴파일 단계에서 타입을 검사할 수 있다.
- 클래스나 메소드 내부에서 사용되는 객체의 타입 안정성을 높일 수 있다
- 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있다

```
import java.util.*;  
  
public interface Stack<E>{  
    void push(E item) throws Exception;  
    E pop() throws Exception;  
    int size();  
    boolean isEmpty();  
    boolean isFull();  
      
}
```

```
import java.util.*;  
  
public class NewStack<E> implements Stack<E>{  
    private E[] elements;  
    private int top ;  
  
    @SuppressWarnings("unchecked")  
    public NewStack(int size) {  
        this.elements  = (E[]) new Object[size];  
        this.top = -1;  
    }  
  
    public void push(E item) throws Exception {  
        if (this.isFull())  
                throw new Exception("Stack Is Full");  
        this.elements[++top] = item;  
    }  
  
    public E pop() throws Exception {// When stack is empty, it throws Exception  
            if (this.isEmpty())  
                throw new Exception("Stack Is Empty");  
        return this.elements[top--];  
    }  
  
    @Override  
    public int size() {  
        return this.elements.length;  
    }  
    
    @Override  
    public boolean isEmpty() {  
        return this.top < 0;  
    }  
    @Override  
    public boolean isFull() {  
        return top == elements.length - 1;  
    }  
  
    public String toString() {  
      String str = "Stack : ";  
      for (int i = 0; i < top; i++)  
         str += elements[i] + ", ";  
  
      return str + elements[top];  
   }  
  
  
}
```

# Stream 
- for문 / Iterator를 사용할 경우 **코드의 복잡도를 개선**하기 위해 고안됨
- Collection에 저장되어 있는 element들을 하나씩 순회하며 처리가 가능하다.
- 생성, 중간 연산, 최종 연산 세 단계의 파이프라인으로 구성 
	- -> 더 찾아보기
- 원본 데이터 소스 변경하지 않음(read-only)
- 일회용임
- 내부 반복자
- `count()`. `max()`, `min()` + `sum()` `average()`

| 스트림(Stream) | 이터레이터(Iterator) |
| --- | --- |
| 병렬 또는 순차적으로 처리할 수 있는 데이터 요소의 시퀀스를 나타냄 | 하나씩 데이터 요소에 접근할 수 있는 시퀀스를 나타냄 |
| 순차 및 병렬 처리 모두 지원함 | 순차 처리만 지원함 |
| 데이터 처리를 위한 API를 제공함 | 데이터 순환 및 제거에 대한 간단한 API만 제공함 |
| 필요할 때에만 데이터를 처리하도록 lazy evaluation를 지원함 | 항상 데이터를 즉시 처리함 |
| filtering, mapping, reducing 및 collecting 등의 작업을 지원함 | Collection에서 요소 제거만 지원함 |
| Collection, 배열 및 기타 소스에서 생성할 수 있음 | Collection 및 배열에서만 생성할 수 있음 |

## lambda
- 메서드를 간단하고 편리하게 표현하기 위해 고안된 문법 요소
- 반환 타입과 이름이 생략 가능해 익명 함수라 하기도 함
- 멀티쓰레드를 활용해 병렬처리를 할 수 있다
- 표현식 : `() -> {}`

## reflexion
- 구체적인 클래스 타입을 알지 못해도 그 클래스의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API
- 런타임 단계에서 클래스, 인터페이스, 필드, 메서드를 검사하거나 수정할 수 있다.
- 객체 인스턴스화, 메소드 호출, 필드 값을 가져오거나 설정할 수 있다.
- 클래스의 인스턴스를 동적으로 생성할 수 있다.
- 객체의 메소드를 동적으로 일으킬(invoke) 수 있다
- 객체의 필드에 접근과 수정을 동적으로 할 수 있다.
- 프레임워크나 IDE에서 reflexion의 동적 바인딩 이용한 기능을 제공함 
	- ex/ 인텔리제이 자동완성 기능, 스트링 어노테이션 등...
## dynamic proxy
- 프록시는 타겟 코드의 수정없이 접근제어 혹은 부가기능을 추가하기 위해 주로 사용되나, 일반적인 프록시는 반복되는 코드가 발생하는 단점이 있음
- 런타임 시점에 프록시 클래스를 만들어주는 방식 
- `newProxyInstance()` Reflexion API 사용
 ```
 @CallerSensitive 
 public static Object newProxyInstance(ClassLoader loader, 
				 Class<?>[] interfaces, 
				 InvocationHandler h)
```
-   `ClassLoader` : 프록시 클래스를 만들 클래스로더
-   `Class` : 프록시 클래스가 구현할 인터페이스 목록(배열)
-   `InvocationHandler` : 메소드가 호출되었을때 실행될 핸들러
	-  `invoke()` 추상메서드 하나만 정의되어있는 걸 볼 수 있다.
		- 동적 프록시의 메소드가 호출되었을 때 이를 대신 실행해주는 메소드.
		- -> 코드 중복을 줄일 수 있음