package com.ws.Thread;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author WS
 *
 */

public class ThreadDemo08 {

	public static void main(String[] args) throws Exception {
		//时间调度轮循的方式
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result = null; 
		try {
			for (int i = 1; i <= 15; i++) {
				result = service.schedule(() -> {
					System.out.println(Thread.currentThread().getName());
					return new Random().nextInt(30);
				}, 2, TimeUnit.SECONDS);
				System.out.println("result:"+result.get());
			}
		} finally {
			service.shutdown();
		}
	}

	private static void testThread() throws InterruptedException, ExecutionException {
		//一池五线程
		//ExecutorService service = Executors.newFixedThreadPool(5);
		//一池一线程
		//ExecutorService service = Executors.newSingleThreadExecutor();
		//自动适配
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> result = null;
		try {
			for (int i = 1; i <= 15; i++) {
				result = service.submit(() -> {
					System.out.print(Thread.currentThread().getName()+" ");
					return new Random().nextInt(30);
				});
				System.out.println("result"+result.get());
			}
		} finally {
			service.shutdown();
		}
	}
}
