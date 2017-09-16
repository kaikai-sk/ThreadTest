package singleton.serializable;

import java.io.Serializable;

public class MyObject implements Serializable
{
	private static final long seiralVersionUID=888L;
	
	public static class MyObjectHandler
	{
		private static final MyObject myObject=new MyObject();
	}
	
	private MyObject()
	{
		
	}
	
	public static MyObject getInstance()
	{
		return MyObjectHandler.myObject;
	}
	
	/**
	 * �÷����ڷ����л�ʱ�ᱻ���ã��÷������ǽӿڶ���ķ������е��Լ���׳ɵĸо�  
	 * @return
	 */
	protected Object readResolve()
	{
		System.out.println("������readResolve����");
		return MyObjectHandler.myObject;
	}
	
}
