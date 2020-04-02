package com.web.pojo;

public class Role {
	private Integer id;
	private String roleName;
	private String createTime;
	
	
	@Override
	public String toString() {
		return "Role [createTime=" + createTime + ", id=" + id + ", roleName="
				+ roleName + "]";
	}
	public Role() {
	}
	public Role(Integer id, String roleName, String createTime) {
		this.id = id;
		this.roleName = roleName;
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
