package com.sk.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest
{
	public static void main(String[] args)
	{
		Queue3 queue3=new Queue3();
		for(int i=0;i<3;i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while(true)
					{
						queue3.get();
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
						queue3.put(new Random().nextInt(10000));
					}
				}
			}).start();
			
		}
	}
}

class Queue3
{
	//共享数据，只能有一个线程写，但可以有多个线程同时读取该数据
	private Object data=null;
	ReadWriteLock rwl=new ReentrantReadWriteLock();
	
	public void put(Object data)
	{
		rwl.writeLock().lock();
		try
		{
			System.out.println(Thread.currentThread().getName()+" be ready to write data!");
			Thread.sleep((long)Math.random()*1000);
			this.data=data;
			System.out.println(Thread.currentThread().getName()+" have wirte data :"+data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			rwl.writeLock().unlock();
		}
	}
	
	public void get()
	{
		rwl.readLock().lock();
		try
		{
			System.out.println(Thread.currentThread().getName()+" be ready to read data!");
			Thread.sleep((long)Math.random()*1000);
			System.out.println(Thread.currentThread().getName()+" have read data : "+data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			rwl.readLock().unlock();
		}
	}
	
	
	
	
}
