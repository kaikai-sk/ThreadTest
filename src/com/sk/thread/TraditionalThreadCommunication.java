package com.sk.thread;

/**
 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次，请写出程序。 
 * @author root
 */
public class TraditionalThreadCommunication
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
}

class Business
{
	private boolean isShouldSub=true;
	
	public synchronized void sub(int i) throws Exception
	{
		while(!isShouldSub)
		{
			this.wait();
		}
		for(int j=0;j<10;j++)
		{
			System.out.println("sub thread sequence of "+j+",loop of"+i);
		}
		isShouldSub=false;
		this.notify();
		
	}
	
	public synchronized void main(int i) throws Exception
	{
		while(isShouldSub)
		{
			this.wait();
		}
		for(int j=0;j<100;j++)
		{
			System.out.println("main thread sequence of "+j+",loop of"+i);
		}
		isShouldSub=true;
		this.notify();
		
	}
	
}

