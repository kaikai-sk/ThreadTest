package singleton.lanhan;

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
			Thread.sleep(3000);
			myObject=new MyObject();
		}
		return myObject;
	}
}
