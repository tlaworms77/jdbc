package com.duzone.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {

	public static void main(String[] args) {
		
		boolean result = update("마이콜", "f", "똘똘이");
		System.out.println(result);
		
		
	}
	
	public static boolean update(String owner, String gender, String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // pakage이름을 적어서 로딩시킨다. classPath에 db 없으면 에러난다.
			// 1.1 library (user-library)에 등록하고 적용시켜야지 path가 잡힌다.

			// 2. 연결하기(Connection 객체 얻어오기) 
			// jdbc:mysql = http://
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비
			String query = "update pet "
					+ "set owner = ?, "
					+ "gender = ? "
					+ "where name = ?";
			
			pstmt = conn.prepareStatement(query);
			
			// 4. Binding - 바인딩작업
			pstmt.setString(1, owner);
			pstmt.setString(2, gender);
			pstmt.setString(3, name);
			
			//5. SQL문 실행 (이미 jdbc에 준비된 곳에 들어간 상태라서 업데이트 실행만)
			int count = pstmt.executeUpdate();
			result = count == 1;
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("sqlerror:" + e);
		} finally {
			try {
				// 처음 실패시 null을 가르키기때문에 또 에러가 나온다.
				if(pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return result;
	}

}
