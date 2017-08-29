package com.sk.thread;

/**
 * ���4���̣߳����������߳�ÿ�ζ�j����1�����������̶߳�jÿ�μ���1��д������ 
 * @author root
 */
public class MultiThreadShareData
{
	private int j;
	
	public static void main(String[] args)
	{
		MultiThreadShareData data=new MultiThreadShareData();
		
		for(int i=0;i<2;i++)
		{
			new Thread(data.new Inc()).start();
			new Thread(data.new Dec()).start();
		}
	}
	
	private synchronized void increase()
	{
		j++;
		System.out.println(Thread.currentThread().getName()+"-inc-"+j);
	}
	private synchronized void decrease()
	{
		j--;
		System.out.println(Thread.currentThread().getName()+"-dec-"+j);
	}
	
	class Dec implements Runnable
	{
		@Override
		public void run()
		{
			for(int i=0;i<100;i++)
			{
				decrease();
			}
		}
	}
	
	class Inc implements Runnable
	{
		@Override
		public void run()
		{
			for(int i=0;i<100;i++)
			{
				increase();
			}
			
		}
		
	}
	
	
}
