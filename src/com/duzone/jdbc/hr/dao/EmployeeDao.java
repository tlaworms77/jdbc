package com.duzone.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public boolean insert(EmployeeVo employeeVo) {
		boolean result = false;

		try {
			// 1. connection 연결
			conn = getConnection();
			// 2. 미리 sql 로드
			String sql = "insert into employee values(null, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 3. query
			String firstName = employeeVo.getFirstName();
			String lastName = employeeVo.getLastName();
			String email = employeeVo.getEmail();
			String tel = employeeVo.getTel();
			String date = employeeVo.getDate();

			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, tel);
			pstmt.setString(5, date);

			int count = pstmt.executeUpdate();

			result = (count == 1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;
	}

	public List<EmployeeVo> getList(String name) {
		
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM employee WHERE first_name like ? or last_name like ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				String tel = rs.getString(5);
				String from_date = rs.getString(6);
				
				EmployeeVo employeeVo = new EmployeeVo();
				employeeVo.setFirstName(firstName);
				employeeVo.setLastName(lastName);
				employeeVo.setEmail(email);
				employeeVo.setTel(tel);
				employeeVo.setDate(from_date);
				list.add(employeeVo);
			}
			
		} catch (SQLException e) {
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
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<EmployeeVo> getList() {
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();

		try {
			conn = getConnection();
			String sql = "select * from employee";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmployeeVo employeeVo = new EmployeeVo();
				employeeVo.setFirstName(rs.getString(2));
				employeeVo.setLastName(rs.getString(3));
				employeeVo.setEmail(rs.getString(4));
				employeeVo.setDate(rs.getString(5));
				list.add(employeeVo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기(Connection 객체 얻어오기)
			// jdbc:mysql = http://
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading fail : " + e);
		}

		return conn;

	}

}
