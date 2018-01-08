package com.ws.ticket2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 线程  操作  数据源
// 高内聚 低耦合

public class SaleTickets {

	public static void main(String[] args) {
		
		Ticket ticket = new Ticket();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					ticket.sale();
				}
			}
		}, "一号").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					ticket.sale();
				}
			}
		}, "二号").start();
	}

}

class Ticket
{
	private int number = 30;
	private Lock lock =  new ReentrantLock();
	
	public void sale() {
		lock.lock();
		try {
			if(number>0) {
				System.out.println(Thread.currentThread().getName()+"窗口:卖出"+ (number--)+"号票,剩余"+number+"张票");
			}
		} finally {
			lock.unlock();
		}
	}

}
