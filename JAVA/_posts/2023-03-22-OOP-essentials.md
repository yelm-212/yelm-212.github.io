---
title:  "[Java] 객체지향 정리"
excerpt: "객체지향에 관련한 내용을 Java언어의 특성과 함께 정리하였습니다."

tags:
  - [Blog, CS, Java, OOP, SOLID, encapsulation, inheritence, polymorphism, abstraction]

toc: true
toc_sticky: true
 
date: 2023-03-22
last_modified_at: 2023-03-22

---

# 객체지향

## 객체지향 모델의 특징 및 객체지향 언어의 장점

Object Oriented Programming languages are defined by the following key words: **abstraction, encapsulation, inheritance, and polymorphism**

1. **캡슐화** : encapsulation
-   관련된 데이터와 알고리즘(코드)이 하나의 묶음으로 정리된 것으로써 개발자가 만들었으며, 관련된 코드와 데이터가 묶여있고 오류가 없어 사용이 편리하다. 
- **데이터를 감추고 외부 세계와의 상호작용은 메소드를 통하는 방법**
- 라이브러리로 만들어 업그레이드하면 쉽게 바꿀 수 있다
-  메소드: 메시지에 따라 실행시킬 프로시저로서 객체지향 언어에서 사용되는 것. 객체지향 언어에서는 메시지를 보내 메소드를 수행시킴으로써 통신(communication)을 수행

- 예시 코드
```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }

	// setter
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

	// getter
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
```
- `BankAccount` 클래스에는 `accountNumber`와 `balance`라는 두 개의 `private` 인스턴스 변수가 있는데, 이러한 `private` 변수는 클래스 외부에서 직접 액세스할 수 없다.

- 이러한 `private` 변수에 대한 제어된 액세스를 제공하기 위해, 클래스는 `accountNumber` 및 `balance`에 대한 `getter` 및 `setter` 메서드를 가지고 있다. 
	- `getAccountNumber` 메서드는 계좌 번호를 반환하며, `setAccountNumber` 메서드는 계좌 번호를 변경할 수 있다. `getBalance` 메서드는 계좌 잔액을 반환한다.

- 클래스에는 계좌에서 돈을 입금하고 출금하는 두 가지 메서드가 있고, 클래스 외부에서 액세스할 수 있다.
- `private` 변수는 클래스 외부에서 숨겨지며, 클래스가 제공하는 공개 메서드를 통해서만 액세스할 수 있다. 이는 **데이터를 보호**하고 외**부 코드에서 데이터를 수정하거나 손상시키는 것을 방지**하여 **안정성을 향상**시키는 데 도움이 된다.

2. **상속** inheritance
-   상속은 **이미 작성된 클래스를 이어 받아서 새로운 클래스를 생성**하는 기법으로 위에서 말한 **기존 코드를 재활용**해서 사용하는 것을 의미한다. 객체지향 방법의 큰 장점중 하나이다.

```
+----------------+
|     Animal     |
+----------------+
| - name: String |
| - age: int     |
+----------------+
| + eat(): void  |
| + sleep(): void|
| + move(): void |
+----------------+
        ^
        |
+-------+-------+
|               |
|      Dog      |
|               |
+---------------+
| + bark(): void|
+---------------+

```
- 위 클래스 다이어그램에서, `Animal` 클래스는 `Dog` 클래스의 슈퍼 클래스이다. 
- `Animal` 클래스는 `name`과 `age`라는 두 개의 `private` 인스턴스 변수와 `eat()`, `sleep()`, `move()`라는 세 개의 `public` 메서드를 가지고 있다. 
- `Dog` 클래스는 `bark()`라는 새로운 `public` 메서드를 추가해 `Animal` 클래스를 상속한다.
- `Animal` 클래스의 `name`과 `age` 변수가 `private`으로 선언되어 있기 때문에 `Dog` 클래스는 `Animal` 클래스에서 상속 받은 메서드를 통해 이 변수들에 접근한다. (`getter, setter`)

3. **다형성** polymorphism
-  하나의 이름(방법)으로 많은 상황에 대처하는 것을 뜻한다.
- **동일한 작업을 하는 함수들에 똑같은 이름을 부여**할 수 있으므로 코드가 더 간단해지는 효과가 있다.

```java
class Helper {

	// Method with 2 integer parameters
	static int Multiply(int a, int b)
	{
		// Returns product of integer numbers
		return a * b;
	}

	// Method 2
	// With same name but with 2 double parameters
	static double Multiply(double a, double b)
	{

		// Returns product of double numbers
		return a * b;
	}
}
  
```

- 위 코드는 같은 이름을 가지고 있으나 메소드의 인자가 정수형인지 실수형인지 여부에 따라 (데이터 타입에 의거해) 다르게 작동한다. (**Compile-time Polymorphism**)

```java
class Parent {

	// Method of parent class
	void Print()
	{
		// Print statement
		System.out.println("parent class");
	}
}

// Class 2
// Helper class
class subclass1 extends Parent {

	// Method
	void Print() { System.out.println("subclass1"); }
}

// Class 3
// Helper class
class subclass2 extends Parent {

	// Method
	void Print()
	{
		// Print statement
		System.out.println("subclass2");
	}
}
}

```
- 위 코드는 메소드 오버라이딩을 수행한다. (**Runtime polymorphism**)

4. 추상화 
- 객체를 **단순화하고 필수적인 특징들만을 추려내어 표현**해, 객체를 더욱 쉽게 이해하고 사용하기 쉽게 만든다
- **객체의 상세 구현을 숨겨**, 외부에서 해당 객체를 사용할 때에는 구현의 복잡성을 신경 쓰지 않고 **객체의 핵심적인 특징과 기능에만 집중**할 수 있다. => 유지보수성과 코드 재사용성이 높아짐

```java
abstract class Shape {
	String color;

	// these are abstract methods
	abstract double area();
	public abstract String toString();

	// abstract class can have the constructor
	public Shape(String color)
	{
		System.out.println("Shape constructor called");
		this.color = color;
	}

	// this is a concrete method
	public String getColor() { return color; }
}
class Circle extends Shape {
	double radius;

	public Circle(String color, double radius)
	{

		// calling Shape constructor
		super(color);
		System.out.println("Circle constructor called");
		this.radius = radius;
	}

	@Override double area()
	{
		return Math.PI * Math.pow(radius, 2);
	}

	@Override public String toString()
	{
		return "Circle color is " + super.getColor()
			+ "and area is : " + area();
	}
}
class Rectangle extends Shape {

	double length;
	double width;

	public Rectangle(String color, double length,
					double width)
	{
		// calling Shape constructor
		super(color);
		System.out.println("Rectangle constructor called");
		this.length = length;
		this.width = width;
	}

	@Override double area() { return length * width; }

	@Override public String toString()
	{
		return "Rectangle color is " + super.getColor()
			+ "and area is : " + area();
	}
}

```
- `Shape` 클래스는 추상 클래스이므로, 객체를 직접 생성할 수 없다.
	- 추상 메소드 `area()`와 `toString()`을 가지고 있으며, 구현되지 않은 메소드로 이를 상속받은 구체 클래스에서 구현해야 한다.
- `Circle` 클래스와 `Rectangle` 클래스에서는 `area()`와 `toString()` 메소드를 각각 구현하여, 해당 도형의 면적과 문자열 표현을 반환한다.
	- `Shape` 클래스에서 추상적으로 선언한 메소드를 구체적으로 구현할 수 있다.

위의 특성들로 인해 생기는 객체지향 방법의 장점은 다음과 같다:
-    신뢰성 있는 소프트웨어를 쉽게 작성할 수 있다. (개발자가 만든 데이터를 사용하기에 신뢰할 수 있다.)
-    코드를 재사용하기 쉽다.
-    업그레이드가 쉽다.
-    디버깅이 쉽다.


## 5원칙(SOLID)

SOLID 원칙은 객체지향 설계에서 적용할 수 있는 가이드라인으로, 유지보수성, 확장성, 재사용성을 높일 수 있는 중요한 원칙이다.

- 단일 책임 원칙 (Single Responsibility Principle)
	-   클래스는 단 하나의 책임만을 가져야 한다.
	-   한 클래스가 너무 많은 일을 하게 되면, 클래스의 변경이 불안정해지고, 재사용성이 낮아지며, 유지보수가 어려워질 수 있다.

- 개방-폐쇄 원칙 (Open/Closed Principle)
	-   확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
	-   새로운 기능을 추가할 때는 기존의 코드를 변경하지 않고, 확장을 통해 추가해야 한다.

 - 리스코프 치환 원칙 (Liskov Substitution Principle)
	-   자식 클래스는 언제나 부모 클래스로 대체할 수 있어야 한다.
	-   자식 클래스는 부모 클래스의 기능을 포함해야 하며, 이러한 기능은 최소한으로 변경되어야 한다.

- 인터페이스 분리 원칙 (Interface Segregation Principle)
	-   클라이언트는 자신이 사용하지 않는 메서드에 의존하도록 강요받지 않아야 한다.
	-   인터페이스는 클라이언트가 필요로 하는 기능에 대해서만 설계되어야 한다.

-  의존 역전 원칙 (Dependency Inversion Principle)
	-   고차원 모듈은 저차원 모듈에 의존해서는 안 되며, 둘 다 추상화에 의존해야 한다.
	-   인터페이스나 추상 클래스와 같은 추상화를 통해 의존성을 역전시켜야 한다.


## 객체지향, 절차지향, 함수형 프로그래밍의 차이점

1.  객체지향 프로그래밍 (OOP) : 객체를 중심으로 코드를 작성하는 패러다임
- 데이터와 해당 데이터를 처리하는 메서드를 하나의 논리적 단위인 객체로 묶어내는 방식이다. 
- 객체 지향 프로그래밍의 주요 특징으로는 캡슐화, 추상화, 상속, 다형성이 있다.
    
2.  절차지향 프로그래밍 (Procedural Programming) : 절차를 중심으로 코드를 작성하는 패러다임
- 문제를 일련의 절차로 분해하고 이를 프로그래밍 코드로 표현한다. 
- 데이터와 메서드는 서로 분리되어 있으며, 함수를 이용하여 처리한다.
    
3.  함수형 프로그래밍 (Functional Programming) : 함수를 중심으로 코드를 작성하는 패러다임
- 입력값과 출력값 간의 관계를 함수로 정의하고 이를 조합하여 프로그램을 작성한다.
- 상태를 변경하는 것이 불가능하며, 함수의 반환값만으로 결과를 예측할 수 있다.

	
