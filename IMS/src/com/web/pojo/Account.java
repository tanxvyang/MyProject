package com.web.pojo;

public class Account {
	private Integer id;
	private String accountNum;//帐号
	private String name;//员工姓名
	private String status;//帐户状态
	private String role;//角色
	
	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", id=" + id + ", name="
				+ name + ", role=" + role + ", status=" + status + "]";
	}
	public Account() {
	}
	public Account(Integer id, String accountNum, String name, String status,
			String role) {
		this.id = id;
		this.accountNum = accountNum;
		this.name = name;
		this.status = status;
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
