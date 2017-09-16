package singleton.staticInnerClass;

public class MyObject
{
	private static class MyObjectHandler
	{
		private static MyObject myObject=new MyObject();
		
	}
	
	
	private MyObject()
	{
		
	}
	
	public static MyObject getInstance() throws Exception
	{
		return MyObjectHandler.myObject;
	}
}
