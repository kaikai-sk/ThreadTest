package com.sk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication2
{
	public static void main(String[] args) throws Exception
	{
		//Business business=new Business();
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
						business.sub2(i);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for(int i=1;i<=50;i++)
				{
					try
					{
						business.sub3(i);
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
		Condition condition1=lock.newCondition();
		Condition condition2=lock.newCondition();
		Condition condition3=lock.newCondition();
		private int shouldSub=1;
		
		public void sub3 (int i)
		{
			lock.lock();
			try
			{
				while(shouldSub!=3)
				{
					try
					{
						condition3.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				for (int j=1;j<=10;j++)
				{
					System.out.println("sub3 thread sequence of "+j + ",loop if "+i);
				}
				shouldSub=1;
				condition2.signal();
			}
			finally
			{
				lock.unlock();
			}
		}
		
		public void sub2 (int i)
		{
			lock.lock();
			try
			{
				while(shouldSub!=2)
				{
					try
					{
						condition2.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				for (int j=1;j<=10;j++)
				{
					System.out.println("sub2 thread sequence of "+j + ",loop if "+i);
				}
				shouldSub=3;
				condition3.signal();
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
				while(shouldSub!=1)
				{
					try
					{
						condition1.await();
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
				shouldSub=2;
				condition2.signal();
				
			}
			finally
			{
				lock.unlock();
			}
		
		}
		
	}
	
}


