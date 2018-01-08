package com.ws.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo06 
{
	public static void main(String[] args) 
	{
		Data data = new Data();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try 
				{
					data.print5(i);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		} , "A").start();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try 
				{
					data.print10(i);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		} , "B").start();
	}
	
}
class Data
{
	private int number = 1;
	
	private Lock lock = new ReentrantLock();
	
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	
	public void print5(int count) throws Exception 
	{
		lock.lock();
		try 
		{
			//判断
			while(number != 1)
			{
				c1.await();
			}
			//干活
			for (int i = 0; i < 5; i++) 
			{
				System.out.println(Thread.currentThread().getName()+i+"\tAA"+count);
			}
			//唤醒
			number = 2;
			c2.signal();
		} finally 
		{
			lock.unlock();
		}
	}
	public void print10(int count) throws Exception 
	{
		lock.lock();
		try 
		{
			//判断
			while(number != 2)
			{
				c2.await();
			}
			//干活
			for (int i = 0; i < 10; i++) 
			{
				System.out.println(Thread.currentThread().getName()+i+"\tBB"+count);
			}
			//唤醒
			number = 1;
			c1.signal();
		} finally 
		{
			lock.unlock();
		}
	}
	
}









