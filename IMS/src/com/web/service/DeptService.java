package com.web.service;

import java.util.List;

import com.web.dao.DeptDao;
import com.web.pojo.Dept;
import com.web.util.Pager;

public interface DeptService {
	public List<Dept> selectAllDept() throws Exception;
	
	public void addDept(Dept dept)throws Exception;
	public void updateDept(Dept dept) throws Exception;
	public void removeDept(String deptNo) throws Exception;
	public Pager<Dept> getDeptByPage(Integer pageNo) throws Exception;
	public Dept getDeptMessage(String deptNo) throws Exception;
	public Dept selectDeptNameByDeptNo(String deptNo)throws Exception;
}
