package singleton.enumEnhance;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyObject
{
	public static Connection getConnection()
	{
		return MyEnumSingleton.connectionFactory.getConnection();
	}
	
	
	
	public enum MyEnumSingleton
	{
		connectionFactory;
		
		private Connection connection;
		
		public Connection getConnection()
		{
			return connection;
		}
		
		private MyEnumSingleton()
		{
			System.out.println("������MyObject����");
			String url="jdbc:sqlserver://localhost:1079;databaseName=ghydb";
			String username="sa";
			String password="";
			String driverName="com.micorosoft.sqlserver.jdbc.SQLServerDriver";
			try
			{
				Class.forName(driverName);
				connection=DriverManager.getConnection(url, username, password);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
