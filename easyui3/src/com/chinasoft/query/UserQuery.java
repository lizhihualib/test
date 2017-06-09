package com.chinasoft.query;

public class UserQuery extends Page{
	private String username;
	private String realname;
	private String startDate;
	private String endDate;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "UserQuery [username=" + username + ", realname=" + realname + ", startDate=" + startDate + ", endDate="
				+ endDate + ", " + super.toString() + "]";
	}
	

}
