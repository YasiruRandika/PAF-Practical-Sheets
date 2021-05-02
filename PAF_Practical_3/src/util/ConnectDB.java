package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 
 * @author W.G. YASIRU RANDIKA 
 * IT19131184
 * 
 * */

/*Create database connection
 *In this project we use MySql Database*/

public class ConnectDB {

	private static Connection connection;

	public ConnectDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Database is created using the given URL, userName and password which is
	 * included in config.properties This will return Connection type object
	 * ClassNotFoundException and SQLException is included
	 */

	public static Connection getDBConnection() {
		/* Create new Connection when the connection is closed or Null */
		try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf",
		 "root", "");
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 catch(SQLException | ClassNotFoundException e)
		 {
		 e.printStackTrace();
		 } 
		return connection;
	}
}