package com.web.dao;

import java.util.List;

import com.web.pojo.Employee;
import com.web.service.EmployeeService;

public interface EmployeeDao {
	public List<Employee> selectAllWorker();
	public Integer countUser(String empNo, String name, String dapt);
	public List<Employee> selectUserByPage(String empNo, String name, String dapt, Integer pageNo, Integer pageSize);
	public void addEmployee(Integer No, String name, String sel1, String sel2,String time);
	public Employee selectEmpByName( String name);
	//用于删除部门前查找部门员工
	public List<Employee>  selectUserBydept(String deptName);
	//统计部门员工数
	public Integer countUser(String deptName);
	//修改信息时查询这个用户的所有信息
	public List<Employee> selectOneEmployee(Integer id);
	//修改用户
	public void updateEmployee(Integer id,String sex,String dept,String entryTime);
	
	public void removeEmployee(Integer id);
	
	public Employee getEmployeeMessage(Integer id);
	
	public Employee selectEmployeeByEmpNo(Integer empNo);
	
	public Employee selectEmployeeByname(String name);
	
	public Employee selectEmployeeByEmpNoAndName(Integer empNo,String name);
	
	public List<Employee>  selectOneNo(String name);
	
	public void updateEmployeeDeptNameByDeptName(String oldName,String newName);
	
}
