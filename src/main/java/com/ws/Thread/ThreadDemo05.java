package com.ws.Thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
*读写锁
*一个线程写，100个线程读取*/

public class ThreadDemo05 {

	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		
		new Thread(() -> {
			mq.write("hello,world");
		},"MyWriteTask") .start();
		
		for (int i = 1; i < 101; i++) {
			new Thread(() -> {
					mq.read();
			}, String.valueOf(i)) .start();
		}
	}

}

class MyQueue{
	private Object obj = null;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public void write(Object obj) {
		rwLock.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public void read() {
		rwLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} finally {
			rwLock.readLock().unlock();
		}
	}
}
