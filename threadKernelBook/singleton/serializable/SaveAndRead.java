package singleton.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndRead
{
	public static void main(String[] args)
	{
		MyObject myObject=MyObject.getInstance();
		
		try
		{
			FileOutputStream fosRef=new FileOutputStream(new File("myObjectFile.txt"));
			ObjectOutputStream oosRef=new ObjectOutputStream(fosRef);
			oosRef.writeObject(myObject);
			oosRef.close();
			fosRef.close();
			System.out.println(myObject.hashCode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		FileInputStream fisRef;
		try
		{
			fisRef = new FileInputStream(new File("myObjectFile.txt"));
			ObjectInputStream oisRef=new ObjectInputStream(fisRef);
			myObject=(MyObject) oisRef.readObject();
			oisRef.close();
			fisRef.close();
			System.out.println(myObject.hashCode());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
