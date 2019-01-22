package com.duzone.jdbc.hr.vo;

public class EmployeeVo {
	private String firstName;
	private String lastName;
	private String email;
	private String tel;
	private String date;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		
		return "fullName:" + lastName + " " + firstName + ", email:" + email + ", tel:" + tel + ", hireDate:" + date;
	}
	
}
