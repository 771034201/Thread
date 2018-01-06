package com.ws.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*AA打印5次，BB打印10次，CC打印15次
* 接着
* AA打印5次，BB打印10次，CC打印15次
* ......来10轮*/

public class ThreadDemo04 {

	public static void main(String[] args) {
		ShareResource sr = new ShareResource();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i < 11; i++) {
					try {
						sr.print5(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		},"A").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i < 11; i++) {
					try {
						sr.print10(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		},"B").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i < 11; i++) {
					try {
						sr.print15(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		},"C").start();
	}
}

class ShareResource{
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	public void print5(int count) throws Exception {
		lock.lock();
		try {
			while (number != 1) {
				c1.await();
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\tAA"+i+"\t"+count);
			}
			number = 2;
			c2.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	public void print10(int count) throws Exception {
		lock.lock();
		try {
			while (number != 2) {
				c2.await();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\tBB"+i+"\t"+count);
			}
			number = 3;
			c3.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	public void print15(int count) throws Exception {
		lock.lock();
		try {
			while (number != 3) {
				c3.await();
			}
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\tCC"+i+"\t"+count);
			}
			number = 1;
			c1.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
}