package com.ws.Thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadDemo07 {

	public static void main(String[] args) {
		ReadWrite rw = new ReadWrite();
		new Thread(() -> {
			rw.write("HelloWorld");
		},"MyWriteTask" ).start();
		
		for (int i = 1; i <= 100; i++) {
			new Thread(() -> {
				rw.read();
			},String.valueOf(i) ).start();
		}
	}
	
}
class ReadWrite{
	
	private Object obj = null;
	
	ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public void write(Object obj) {
		rwLock.readLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} finally {
			rwLock.readLock().unlock();
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
