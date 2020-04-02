package com.web.pojo;

public class Menu {
	private Integer id;
	private String menuName;
	private String createTime;
	
	
	@Override
	public String toString() {
		return "Menu [createTime=" + createTime + ", id=" + id + ", menuName="
				+ menuName + "]";
	}
	public Menu() {
	}
	public Menu(Integer id, String menuName, String createTime) {
		this.id = id;
		this.menuName = menuName;
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
