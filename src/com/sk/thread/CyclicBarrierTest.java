package com.sk.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �߳�ͬ���Ĺ���
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
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�1����ǰ����" + (cyclicBarrier.getNumberWaiting()+1) + "���Ѿ����"
								+ (cyclicBarrier.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));						
						cyclicBarrier.await();
						
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�2����ǰ����" + (cyclicBarrier.getNumberWaiting()+1)
								+ "���Ѿ����" + (cyclicBarrier.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cyclicBarrier.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�3����ǰ����" + (cyclicBarrier.getNumberWaiting() + 1) + "���Ѿ����" + (cyclicBarrier.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));						
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
