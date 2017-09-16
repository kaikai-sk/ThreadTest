package singleton.keyCodeConcurrent;

public class MyThread extends Thread
{
	@Override
	public void run()
	{
		try
		{
			System.out.println(MyObject.getInstance().hashCode());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
