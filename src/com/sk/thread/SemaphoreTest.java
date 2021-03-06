package com.sk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号灯的使用
 * @author root
 */
public class SemaphoreTest
{
	public static void main(String[] args)
	{
		ExecutorService service=Executors.newCachedThreadPool();
		Semaphore semaphore=new Semaphore(3);
		for(int i=0;i<10;i++)
		{
			Runnable runnable=new Runnable()
			{
				
				@Override
				public void run()
				{
					try
					{
						semaphore.acquire();
						System.out.println("线程"+Thread.currentThread().getName()+"进入，当前已有"+(3-
								semaphore.availablePermits())+"个并发");
						Thread.sleep((long)Math.random()*10000);
						System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
						semaphore.release();
						System.out.println("线程"+Thread.currentThread().getName()+"已离开，"
								+ "当前已有"+(3-semaphore.availablePermits())+"个并发");
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				
				}
			};
			service.execute(runnable);
		}
	}
}
