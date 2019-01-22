package com.duzone.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {

		Connection conn = null;

		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.duzone.driver.MyDriver");

			// 2. 연결하기 jdbc:mysql = http://
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결 성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(conn != null)
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
