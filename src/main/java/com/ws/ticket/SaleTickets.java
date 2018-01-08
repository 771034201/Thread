package com.ws.ticket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTickets {

	public static void main(String[] args) throws Exception {
		
		Ticket ticket = new Ticket();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 140; i++) {
				ticket.sale();
			}
		} , "A").start(); 
		
		
		
	/*	new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					ticket.sale();
				}
			}
		}, "A").start();*/
		
		new Thread(() ->  {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 140; i++) {
				ticket.sale();
			}
		}, "B").start();
		
	
		
		new Thread(() ->  {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 140; i++) {
				ticket.sale();
			}
		}, "C").start();
		
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					ticket.sale();
				}
			}
		},"B").start();
		*/
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					ticket.sale();
				}
			}
		},"C").start();
*/	}
}
class Ticket{
	private int number = 130;
	private Lock lock = new ReentrantLock();
	
	public void sale() {
		lock.lock();
		try {
			if(number>0) {
				System.out.println(Thread.currentThread().getName()+"卖出了:"+(number--)+"号票"+",还剩"+number+"张");
			}
		} finally {
			lock.unlock();
		}
	}
}