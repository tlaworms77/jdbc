package com.duzone.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
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
			String query = "select name, owner, birth from pet";
			// select 한 결과를 담을 객체 ResultSet
			rs = stmt.executeQuery(query); // select -> executeQuery(query)
			
			//5. 결과 가져오기
			while(rs.next()) {
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String birth = rs.getString(3);
				
				System.out.println(name + ":" + owner + ":" + birth);
				
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("sqlerror:" + e);
		} finally {
			try {
				// 처음 실패시 null을 가르키기때문에 또 에러가 나온다.
				if( rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
