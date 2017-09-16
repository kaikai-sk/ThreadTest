package singleton.keyCodeConcurrent;

public class MyObject
{
	private static MyObject myObject;
	
	private MyObject()
	{
		
	}
	
	public static MyObject getInstance() throws Exception
	{
		if(myObject!=null)
		{
		}
		else
		{
			synchronized (MyObject.class)
			{
				myObject=new MyObject();
			}
		}
		return myObject;
	}
}
