package com.web.pojo;

import java.util.Date;

/**
 * 12-12  15:31 
 * 报销实体类
 * @author soft01
 *
 */
public class Expense {
	private String expNo;  //报销编号
	private Integer empId;  //员工id
	private String username;//姓名
	private String type;//报销类型
	private Double money;//金额
	private String expStatus;//状态
	private Date createTime;//创建时间
	private String about;
	
	
	
	@Override
	public String toString() {
		return "Expense [about=" + about + ", createTime=" + createTime
				+ ", empId=" + empId + ", expNo=" + expNo + ", expStatus="
				+ expStatus + ", money=" + money + ", type=" + type
				+ ", username=" + username + "]";
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Expense() {
		super();
	}
	public Expense(String expNo, Integer empId, String username, String type,
			Double money, String expStatus, Date createTime,String about) {
		super();
		this.expNo = expNo;
		this.empId = empId;
		this.username = username;
		this.type = type;
		this.money = money;
		this.expStatus = expStatus;
		this.createTime = createTime;
		this.about =about;
	}
	
	public String getExpNo() {
		
		//修改显示值
		return "BX"+expNo;
		
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		//报销类型 1 差旅费 2 招待费 3 办公费
		if (type.equals("差旅费")) {
			return "1";
		}
		if (type.equals("招待费")) {
			return "2";
		}
		if (type.equals("办公费")) {
			return "3";
		}
		if (type.equals("1")) {
			return "差旅费";
		}
		if (type.equals("2")) {
			return "招待费";
		}
		if (type.equals("3")) {
			return "办公费";
		}
		return null;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getExpStatus() {
		//#状态 0 草稿 1 提交
		if(expStatus.equals("草稿")){
			return "0";
		}
		if (expStatus.equals("提交")) {
			return "1";
		}
		if (expStatus.equals("0")) {
			return "草稿";
		}
		if (expStatus.equals("1")) {
			return "已提交";
		}
		
		return null;
	}
	public void setExpStatus(String expStatus) {
		this.expStatus = expStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
