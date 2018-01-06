package com.ws.Thread;

public class SingletonDemo {
	
	private SingletonDemo(){
		System.out.println(Thread.currentThread().getName());
	}
	
	private static volatile SingletonDemo instance = null;
	
	public static SingletonDemo getInstance() {
		if(instance == null) {
			synchronized (SingletonDemo.class) {
				if(instance == null) {
					instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}
	public static void main(String[] args) {
		new Thread(() -> {
			SingletonDemo.getInstance();
		},"A").start();
		
	}
	
}
