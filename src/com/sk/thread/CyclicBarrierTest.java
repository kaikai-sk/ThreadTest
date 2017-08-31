package com.sk.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程同步的工具
 * @author root
 */
public class CyclicBarrierTest
{
	public static void main(String[] args)
	{
		ExecutorService service=Executors.newCachedThreadPool();
		CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
		for(int i=0;i<3;i++)
		{
			Runnable runnable=new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						Thread.sleep((long)Math.random()*10000);
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点1，当前已有" + (cyclicBarrier.getNumberWaiting()+1) + "个已经到达，"
								+ (cyclicBarrier.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));						
						cyclicBarrier.await();
						
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点2，当前已有" + (cyclicBarrier.getNumberWaiting()+1)
								+ "个已经到达，" + (cyclicBarrier.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));
						cyclicBarrier.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点3，当前已有" + (cyclicBarrier.getNumberWaiting() + 1) + "个已经到达，" + (cyclicBarrier.getNumberWaiting()==2?"都到齐了，继续走啊":"正在等候"));						
						cyclicBarrier.await();						
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
	
}
