package com.ws.Thread;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//线程池，近似的理解java里面第4种获得多线程的方式
public class TreadDemo05 {

	public static void main(String[] args) {
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		
		ScheduledFuture<Integer> result = null;
		
		try {
			for (int i = 1; i < 16; i++) {
				result  = service.schedule(() -> {
					System.out.println(Thread.currentThread().getName());
					return new Random().nextInt(30);
				}, 1, TimeUnit.SECONDS);
				System.out.println("result:"+result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			service.shutdown();
		}

	}

}
