package com.web.pojo;

import java.util.Date;

public class Permissions {
	private Integer id;
	private Integer roleId;
	private Integer menuId;
	private Date createDate;
	
	
	
	@Override
	public String toString() {
		return "permissions [createDate=" + createDate + ", id=" + id
				+ ", menuId=" + menuId + ", roleId=" + roleId + "]";
	}
	public Permissions() {
	}
	public Permissions(Integer id, Integer roleId, Integer menuId,
			Date createDate) {
		this.id = id;
		this.roleId = roleId;
		this.menuId = menuId;
		this.createDate = createDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
