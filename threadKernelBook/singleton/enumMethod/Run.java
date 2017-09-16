package singleton.enumMethod;

public class Run
{
	public static void main(String[] args) throws Exception
	{
		MyThread[] myThreads=new MyThread[15];
		for(int i=0;i<myThreads.length;i++)
		{
			myThreads[i]=new MyThread();
		}
		for(int i=0;i<myThreads.length;i++)
		{
			myThreads[i].start();
		}
	}
}
