package com.sk.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest
{
	public static void main(String[] args)
	{
		ExecutorService service = Executors.newCachedThreadPool();
		CountDownLatch cdOrder = new CountDownLatch(1);
		CountDownLatch cdAnswer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++)
		{
			Runnable runnable = new Runnable()
			{
				@Override
				public void run()
				{
					System.out.println("�߳�" + Thread.currentThread().getName() + "��׼����������");
					try
					{
						cdOrder.await();
						System.out.println("�߳�" + Thread.currentThread().getName() + "�ѽ�������");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�" + Thread.currentThread().getName() + "��Ӧ�������");
						cdAnswer.countDown();
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		
		try
		{
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("�߳�" + Thread.currentThread().getName() + "������������");
			cdOrder.countDown();
			System.out.println("�߳�" + Thread.currentThread().getName() + "�ѷ���������ڵȴ����");
			cdAnswer.await();
			System.out.println("�߳�" + Thread.currentThread().getName() + "���յ�������Ӧ���");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		service.shutdown();
	}
}
