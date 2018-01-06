package com.ws.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadDemo01 {

	public static void main(String[] args) throws Exception {
		
		FutureTask<Object> ft = new FutureTask<Object>(new MyThread());
		
		new Thread(ft,"A" ).start();
		Object result = ft.get(1, TimeUnit.SECONDS);
	//	Object result = ft.get();
		System.out.println(result);
	}
}

class MyThread implements Callable<Object>{

	@Override
	public Object call() throws Exception {
		Thread.sleep(2000);
		System.out.println("callable 接口被调用了");
		return "调用完成";
	}
	
}

