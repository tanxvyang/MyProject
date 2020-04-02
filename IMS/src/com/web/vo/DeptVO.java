package com.web.vo;

import com.web.pojo.Dept;

public class DeptVO extends Dept{
	private Dept dept;
	private Integer number;
	
	public DeptVO() {
		super();
	}
	public DeptVO(Dept dept, Integer number) {
		super();
		this.dept = dept;
		this.number = number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getNumber() {
		return number;
	}
	
}
