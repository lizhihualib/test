package com.chinasoft.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * jdbc工具类
 * @author Administrator
 *
 */
public class JdbcUtil {
	/**
	 * 获得连接connection
	 * @return
	 */
	public static Connection getConn(){
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/easyui?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return conn;
	}
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
