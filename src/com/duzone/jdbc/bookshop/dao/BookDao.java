package com.duzone.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.duzone.jdbc.bookshop.vo.BookVo;

public class BookDao {

/*	public List<BookVo> getList(String title) {
	
		}*/

	

	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			// 3. PreparedStatement 객체를 생성
			String sql = "insert "
						+ "into book "
						+ "values(null, ?,'대여가능', ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setLong(2, bookVo.getAuthorNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("sqlerror:" + e);
		} finally {
			try {
				// 처음 실패시 null을 가르키기때문에 또 에러가 나온다.
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;
	}
	
	//다가져오는 메소드 오버로드 (매개변수가 없을 때)
	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. Statement 객체를 생성
			stmt = conn.createStatement();

			// 4. SQL문 실행
			String query = "SELECT "
							+ "a.no, a.title, a.status, b.name "
						 + "FROM "
						 	+ "book a, author b "
						 + "WHERE "
							+ "a.author_no = b.no "
						 + "ORDER BY a.no ASC";
			// select 한 결과를 담을 객체 ResultSet
			rs = stmt.executeQuery(query); // select -> executeQuery(query)

			// 5. 결과 가져오기
			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
				list.add(vo);
				
			}

		}catch (SQLException e) {
			System.out.println("sqlerror:" + e);
		} finally {
			try {
				// 처음 실패시 null을 가르키기때문에 또 에러가 나온다.
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;
	}

	@SuppressWarnings("resource")
	public boolean updateStatus(long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "select * from book where no = ? and status = '대여가능'";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			// resultSet에 담긴 결과가 없을 때 -> No Search Found Table! 
			if(!rs.next()) {
				System.out.println("이미 대여중이거나 항목을 잘못 선택하셨습니다.");
			} else {
				sql = "update book set status = '대여중' where no = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, no);
				
				int count = pstmt.executeUpdate();
				result = (count == 1);
				System.out.println("대여완료");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("자원해제 실패 : " + e);
			}
		}
		
		return result;
	}

	public boolean returnBook(long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
	
			String sql = "update book set status = '대여가능' where no = ?";
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setLong(1, no);
				
			int count = pstmt.executeUpdate();
			result = (count == 1);
			System.out.println("반납완료");
			
		} catch (SQLException e) {
			System.out.println("대여실패 : " + e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("자원해제 실패 : " + e);
			}
		}
		
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			// pakage이름을 적어서 로딩시킨다. classPath에 db 없으면 에러난다.
			// 1.1 library (user-library)에 등록하고 적용시켜야지 path가 잡힌다.

			// 2. 연결하기(Connection 객체 얻어오기)
			// jdbc:mysql = http://
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}

}
