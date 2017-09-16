package singleton.enumMethod;

import java.sql.Connection;
import java.sql.DriverManager;

public enum MyObject
{
	connectionFactory;
	
	private Connection connection;
	
	public Connection getConnection()
	{
		return connection;
	}
	
	private MyObject()
	{
		System.out.println("调用了MyObject构造");
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
