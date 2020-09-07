package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
	public static Connection getSQLServerConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String hostName = "localhost";
		String database = "Business_Management";
		String userName = "sa";
		String passWord = "123456"; 
		String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
	             + ";databaseName=" + database;
	     Connection conn = (Connection) DriverManager.getConnection(connectionURL, userName, passWord);
	     return conn;
	}
}
