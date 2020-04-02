package com.web.dao;

import java.util.List;
import com.web.pojo.Dept;

public interface DeptDao {
	public Integer countDept();
	public void insertDept(Dept dept);
	public void updateDept(Dept dept);
	public void deleteDept(String deptNo );
	public List<Dept> selectAllDept();
	public List<Dept> selectDeptByDepyNoName(Dept dept);
	public List<Dept> selectDeptByPage(Integer pageNo, Integer pageSize);
	//12-11
	public Dept selectDeptBydeptNo(String deptNo);
	
	//12-12
	public Dept selectDeptByName(String name);
	//12-12
	public Dept selectDeptBydeptNoAndName(Dept dept);
	public Dept selectDeptBydeptName(String deptName);
	
}
