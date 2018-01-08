package com.ws.Lamda;

public class MyLambda {

	public static void main(String[] args) {
		
		Foo foo = (x,y) -> {
			return x/y;
		};
		int result = foo.div(10, 2);
		System.out.println(result);
		
		
		Foo foo2 = (x,y) -> {
			return x+y;
		};
		
		int result2 = Foo.add(2,3);
		System.out.println(result2);
	}
		
		/*Foo foo  = new Foo() {
			@Override
			public void sayHello() {
				System.out.println("hello");
			}
		};
		foo.sayHello();
	}*/
}

@FunctionalInterface
interface Foo
{
	//public void sayHello();
	//public int add(int x,int y);
	public int del(int x,int y);
	
	default int div(int x , int y) {
		return x/y;
	}
	
	static int add(int x, int y) {
		return x+y;
	}
}
