package mySQL_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/patientsDB";
	
	public static Connection getConnection() {
		
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL, "root", "123456");
			
			if(connection != null) {
//				System.out.println("DB연결 성공!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
