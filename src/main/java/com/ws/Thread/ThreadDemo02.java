package com.ws.Thread;
// 题目：现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，实现交替，来5轮，变量初始值为零。

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//线程      操作   数据源
// 高内聚 低耦合
public class ThreadDemo02 {
	
	public static void main(String[] args) {
		ShareData sd = new ShareData();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					sd.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "A").start();
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					sd.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} , "B").start();
	}
		
}
class ShareData{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		try {
			while(number != 0) {
				condition.await();
			}
			++number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}
	public void decrement() throws Exception {
		lock.lock();
		try {
			while(number == 0) {
				condition.await();
			}
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}
}
