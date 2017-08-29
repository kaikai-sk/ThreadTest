package com.sk.thread;

public class TraditionalThreadSynchronized
{
	public static void main(String[] args)
	{
		new TraditionalThreadSynchronized().init();
	}
	
	private void init()
	{
		Outputer outputer=new Outputer();
		new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				while(true)
				{
					try
					{
						Thread.sleep(10);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
				
			}
		}).start();
		
		new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				while(true)
				{
					try
					{
						Thread.sleep(10);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("lihonggming");
				}
				
			}
		}).start();
		
	}
	
	static class Outputer
	{
		public void output(String name)
		{
			synchronized (Outputer.class)
			{
				for (int i=0;i<name.length();i++)
				{
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		public static synchronized void output3(String name)
		{
			int len=name.length();
			for(int i=0;i<name.length();i++)
			{
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
	}
	
}
