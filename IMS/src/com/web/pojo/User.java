package com.web.pojo;

public class User {
	private Integer tid;
	private String account;
	private String password;
	private String empNo;
	private String empNa;
	private Integer roleId;
	private String createTime;
	
	
	
	@Override
	public String toString() {
		return "User [account=" + account + ", createTime=" + createTime
				+ ", empNa=" + empNa + ", empNo=" + empNo + ", password="
				+ password + ", roleId=" + roleId + ", tid=" + tid + "]";
	}
	public User() {
	}
	public User(Integer tid, String account, String password, String empNo,
			String empNa, Integer roleId, String createTime) {
		this.tid = tid;
		this.account = account;
		this.password = password;
		this.empNo = empNo;
		this.empNa = empNa;
		this.roleId = roleId;
		this.createTime = createTime;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setEmpNa(String empNa) {
		this.empNa = empNa;
	}
	public String getEmpNa() {
		return empNa;
	}
	
	
}