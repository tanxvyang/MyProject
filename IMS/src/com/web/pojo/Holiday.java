package com.web.pojo;

import java.util.Date;

public class Holiday {
	private Integer id;
	private String username;//姓名
	private String type;//请假类型
	private String bz;//请假事由
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String status;//请假状态
	private Date createTime;//创建时间
	
	@Override
	public String toString() {
		return "Holiday [bz=" + bz + ", createTime=" + createTime
				+ ", endTime=" + endTime + ", id=" + id + ", startTime="
				+ startTime + ", status=" + status + ", type=" + type
				+ ", username=" + username + "]";
	}
	public Holiday() {
	}
	public Holiday(Integer id, String username, String type, String bz,
			String startTime, String endTime, String status, Date createTime) {
		this.id = id;
		this.username = username;
		this.type = type;
		this.bz = bz;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
