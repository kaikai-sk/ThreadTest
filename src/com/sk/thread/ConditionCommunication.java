package com.sk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication
{
	public static void main(String[] args) throws Exception
	{
		Business business=new Business();
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for(int i=1;i<=50;i++)
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
		
		for(int i=1;i<=50;i++)
		{
			business.main(i);
		}
		
	}
	static class Business
	{
		Lock lock=new ReentrantLock();
		Condition condition=lock.newCondition();
		private boolean bShouldSub=true;
		
		public void sub (int i)
		{
			lock.lock();
			try
			{
				while(!bShouldSub)
				{
					try
					{
						condition.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				for (int j=1;j<=10;j++)
				{
					System.out.println("sub thread sequence of "+j + ",loop if "+i);
				}
				bShouldSub=false;
				condition.signal();
			}
			finally
			{
				lock.unlock();
			}
		}
		
		public void main(int i)
		{
			lock.lock();
			try
			{
				while(bShouldSub)
				{
					try
					{
						condition.await();
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int j=1;j<=100;j++)
				{
					System.out.println("main thread sequence of "+j + ",loop if "+i);
				}
				bShouldSub=true;
				condition.signal();
				
			}
			finally
			{
				lock.unlock();
			}
		
		}
		
	}
	
}


