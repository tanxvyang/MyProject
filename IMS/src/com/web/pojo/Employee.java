package com.web.pojo;

public class Employee {
	private Integer id;
	private Integer empNo;
	private String name;
	private String dept;
	private String sex;
	private String education;
	private String email;
	private String phone;
	private String entryTime;
	private String createTime;
	
	
	
	@Override
	public String toString() {
		return "Employee [createTime=" + createTime + ", dept=" + dept
				+ ", education=" + education + ", email=" + email + ", empNo="
				+ empNo + ", entryTime=" + entryTime + ", id=" + id + ", name="
				+ name + ", phone=" + phone + ", sex=" + sex + "]";
	}
	public Employee() {
	}
	public Employee(Integer id, Integer empNo, String name, String dept,
			String sex, String education, String email, String phone,
			String entryTime, String createTime) {
		this.id = id;
		this.empNo = empNo;
		this.name = name;
		this.dept = dept;
		this.sex = sex;
		this.education = education;
		this.email = email;
		this.phone = phone;
		this.entryTime = entryTime;
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
