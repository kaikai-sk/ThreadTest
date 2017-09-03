package com.sk.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次，请写出程序。 
 * @author root
 */
public class BlockingQueueCommunication
{
	public static void main(String[] args)
	{
		Business business=new Business();
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i=0;i<50;i++)
				{
					try
					{
						business.sub(i);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		for(int i=0;i<50;i++)
		{
			try
			{
				business.main(i);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	
	static class Business
	{
		BlockingQueue<Integer> queue1=new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2=new ArrayBlockingQueue<Integer>(1);
		
		//匿名构造方法，在所有的构造方法之前运行
		{
			try
			{
				queue2.put(1);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		public void sub(int i) throws Exception
		{
			queue1.put(1);
			for(int j=0;j<10;j++)
			{
				System.out.println("sub thread sequence of "+j+",loop of"+i);
			}
			queue2.take();
			
		}
		
		public void main(int i) throws Exception
		{
			queue2.put(1);
			for(int j=0;j<100;j++)
			{
				System.out.println("main thread sequence of "+j+",loop of"+i);
			}
			queue1.take();
		}
		
	}
}


