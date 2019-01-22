package com.duzone.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {

	public static void main(String[] args) {
		
		boolean result = delete("마음이2");
		System.out.println(result);
	}

	public static boolean delete(String name) {
		Connection conn = null;
		Statement stmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // pakage이름을 적어서 로딩시킨다. classPath에 db 없으면 에러난다.
			// 1.1 library (user-library)에 등록하고 적용시켜야지 path가 잡힌다.

			// 2. 연결하기(Connection 객체 얻어오기) 
			// jdbc:mysql = http://
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 객체를 생성
			stmt = conn.createStatement();

			//4. SQL문 실행
			String query = "delete from pet where name = '" + name + "'";
			int count = stmt.executeUpdate(query);
			result = count == 1;
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("sqlerror:" + e);
		} finally {
			try {
				// 처음 실패시 null을 가르키기때문에 또 에러가 나온다.
				if(stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return result;
	}
	
}
