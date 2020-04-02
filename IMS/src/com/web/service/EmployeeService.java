package com.web.service;

import java.util.List;

import com.web.pojo.Employee;
import com.web.util.Pager;

public interface EmployeeService {
	
	public Pager<Employee> selectAll(Integer pageNo, String empNo, String name, String dapt) throws Exception ;

	public void addEmployee(Integer No,String name,String sel1,String sel2,String time)throws Exception;

	public List<Employee> selectOneEmployee(Integer id) throws Exception;
	
	public void updateEmployee(Integer id,String sex,String dept,String entryTime)throws Exception;

	public void removeEmployee(Integer id)throws Exception;
	
	public Employee getEmployeeMessage(Integer id)throws Exception;
	
	public Employee selectEmployeeByEmpNoAndName(Integer empNo,String name) throws Exception;
	
	public List<Employee> selectOneNo(String name) throws Exception;
	
	public Employee selectEmployeeNoByName(String name) throws Exception;
	
	public Employee selectEmployeeNameByEmpNo(Integer empNo)throws Exception;
	
	public void updateEmployeeDeptNameByDeptName(String oldName,String newName)throws Exception;
}
  