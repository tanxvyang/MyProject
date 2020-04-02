package com.web.pojo;

import java.util.Date;

public class Dept {
	private String deptNo;
	private String deptName;
	private String deptLoc;
	private String deptManager;
	private Date createDate;
	private Integer deptEmpNumber;
	
	
	public Dept() {
		super();
	}
	public Dept(String deptNo, String deptName, String deptLoc,
			String deptManager, Date createDate, Integer deptEmpNumber) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.deptManager = deptManager;
		this.createDate = createDate;
		this.deptEmpNumber = deptEmpNumber;
	}
	public Integer getDeptEmpNumber() {
		return deptEmpNumber;
	}
	public void setDeptEmpNumber(Integer deptEmpNumber) {
		this.deptEmpNumber = deptEmpNumber;
	}
	
	
	
	@Override
	public String toString() {
		return "Dept [createDate=" + createDate + ", deptEmpNumber="
				+ deptEmpNumber + ", deptLoc=" + deptLoc + ", deptManager="
				+ deptManager + ", deptName=" + deptName + ", deptNo=" + deptNo
				+ "]";
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptLoc() {
		return deptLoc;
	}
	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}
	public String getDeptManager() {
		return deptManager;
	}
	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
