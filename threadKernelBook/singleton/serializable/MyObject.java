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
	 * 该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉  
	 * @return
	 */
	protected Object readResolve()
	{
		System.out.println("调用了readResolve方法");
		return MyObjectHandler.myObject;
	}
	
}
