package singleton.volatileTest;

public class RunThread extends Thread
{

	// ����ע���volatile�ؼ��ֺͲ��ӵ����н��
	 private boolean isRunning = true;
//	private volatile boolean isRunning = true;

	private void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public void run()
	{
		System.out.println("����run����..");
		int i = 0;
		while (isRunning == true)
		{// ����volatile��ʶ�ı��������ڴ��еĿɼ��Խ���������
			// ..
		}
		System.out.println("�߳�ֹͣ");
	}

	public static void main(String[] args) throws InterruptedException
	{
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning��ֵ�Ѿ���������false");
	}
}