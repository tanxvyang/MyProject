package com.web.pojo;

public class Merge {
	private Integer id;
	private Integer roleId;
	private String roleName;
	private Integer menuId;
	private String menuName;
	
	
	@Override
	public String toString() {
		return "Merge [id=" + id + ", menuId=" + menuId + ", menuName="
				+ menuName + ", roleId=" + roleId + ", roleName=" + roleName
				+ "]";
	}
	public Merge() {
	}
	public Merge(Integer id, Integer roleId, String roleName, Integer menuId,
			String menuName) {
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.menuId = menuId;
		this.menuName = menuName;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
}
