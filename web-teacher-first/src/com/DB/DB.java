package com.DB;
import java.sql.Connection;
import java.sql.DriverManager;

//连接数据库
public class DB {
	private static String qudong="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=UTF-8";
	private static String user="root";
	private static String pwd="root";
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName(qudong);
			con=DriverManager.getConnection(url,user,pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;	
	}
}
