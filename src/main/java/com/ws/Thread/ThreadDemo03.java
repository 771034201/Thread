package com.ws.Thread;
/*
*多线程的锁（8锁）
*1	标准访问，先打印苹果还是Android   				
*2 新增暂停4秒钟方法，先打印苹果还是Android   			
*3 新增hello方法，先打印苹果还是hello  				
*4 有两部手机，先打印苹果还是Android     			
*5 两个静态同步方法，有一部手机，先打印苹果还是Android		//苹果
*6 两个静态同步方法，有两部手机，先打印苹果还是Android		//苹果 锁的是整个class类对象
*7 1个普通同步方法，1个静态同步方法，有1部手机，先打印苹果还是Android  	//Android  静态同步方法锁的类对象,一旦其中一个静态线程调用了其中的一个同步方法,整个类都会被锁住,而类中,因为
*																由于苹果有延迟机制,所以延迟后调用
*8 1个普通同步方法，1个静态同步方法，有2部手机，先打印苹果还是Android*/   //Android   静态同步方法锁的class类对象,
																// 而两把锁就是两个不同的对象,静态同步方法
																// 与非静态同步方法之间是不会产生竞态关系的

public class ThreadDemo03 {

	public static void main(String[] args) {
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		
		new Thread(() -> {
			try {
				//phone.IOSPhone();
				Phone.IOSPhone();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"A" ).start();
		
		new Thread(() -> {
			phone.Android();
			//phone2.Android();
		},"B" ).start();
	}

}
class Phone{
	
	public static synchronized void IOSPhone() throws Exception{
		System.out.println(Thread.currentThread().getName()+"\tIOS");
	}
	
	public  synchronized void Android() {
		System.out.println(Thread.currentThread().getName()+"\tandroid");
	}
	
	public void hello() {
		System.out.println(Thread.currentThread().getName()+"\thello");
	}
}