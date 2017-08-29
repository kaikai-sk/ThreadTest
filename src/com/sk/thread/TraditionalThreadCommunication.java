package com.sk.thread;

/**
 * ���߳�ѭ��10�Σ��������߳�ѭ��100�������ֻص����߳�ѭ��10�Σ������ٻص����߳���ѭ��100�����ѭ��50�Σ���д������ 
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

