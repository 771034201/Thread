package com.ws.Thread;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch:让一些线程阻塞,直到一些线程完成后才被唤醒
 * @author WS
 *
 */
public class ThreadDemo10 {

	public static void main(String[] args) throws Exception {
		
		CountDownLatch countDownLatch = new CountDownLatch(6);
		
		try {
			for (int i = 1; i <= 6; i++) {
				new Thread(() -> {
					System.out.println(Thread.currentThread().getName()+"同学离开教室");
					countDownLatch.countDown();
				} ,String.valueOf(i)).start();
			}
		} finally {
			countDownLatch.await();
			System.out.println("班级锁门");
		}
		
	}

}
