package com.sk.thread;

import java.util.Random;

public class ThreadLocalTest
{

	public static void main(String[] args)
	{
		for(int i=0;i<2;i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					int data=new Random().nextInt();
					MyThreadScopeData.getThreadInstance().setName("name"+data);
					MyThreadScopeData.getThreadInstance().setAge(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A
	{
		public void get()
		{
			MyThreadScopeData myData=MyThreadScopeData.getThreadInstance();
			System.out.println("A from "+Thread.currentThread().getName()+" getData "+
			myData.getName()+","+myData.getName());
		}
	}
	
	static class B
	{
		public void get()
		{
			MyThreadScopeData myData=MyThreadScopeData.getThreadInstance();
			System.out.println("B from "+Thread.currentThread().getName()+" getData "+
			myData.getName()+","+myData.getName());
		}
	}
}

class MyThreadScopeData
{
	private static ThreadLocal<MyThreadScopeData> map=new ThreadLocal<MyThreadScopeData>();
	
	public static MyThreadScopeData getThreadInstance()
	{
		MyThreadScopeData instance=map.get();
		if(instance==null)
		{
			instance=new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	
	private MyThreadScopeData()
	{
		
	}
	
	
	private String name;
	private int age;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	
}
