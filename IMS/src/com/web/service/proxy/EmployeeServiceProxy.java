package com.web.service.proxy;

import java.util.List;

import com.web.dao.EmployeeDao;
import com.web.pojo.Account;
import com.web.pojo.Dept;
import com.web.pojo.Employee;
import com.web.pojo.User;
import com.web.service.EmployeeService;
import com.web.service.UserService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class EmployeeServiceProxy implements EmployeeService{
	private EmployeeService employeeService;
	private Transaction trans;
	
	public Pager<Employee> selectAll(Integer pageNo, String empNo,
			String name, String dapt) throws Exception {
		trans.begin();
		Pager<Employee> Pager;
		try {
			Pager = employeeService.selectAll(pageNo,empNo,name,dapt);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return Pager;
	}
	
	public void addEmployee(Integer No, String name, String sel1, String sel2,
			String time) throws Exception {
		trans.begin();
		try {
			employeeService.addEmployee(No, name, sel1, sel2, time);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	
	public List<Employee> selectOneEmployee(Integer id) throws Exception {
		trans.begin();
		List<Employee> list = null;
		try {
			list = employeeService.selectOneEmployee(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	public void updateEmployee(Integer id,
			String sex, String dept, String entryTime) throws Exception {
		trans.begin();
		try {
			employeeService.updateEmployee(id, sex, dept, entryTime);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}
	
	
	public void removeEmployee(Integer id) throws Exception {
		trans.begin();
		try {
			employeeService.removeEmployee(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	public Employee getEmployeeMessage(Integer id) throws Exception {
		trans.begin();
		Employee employee ;
		try {
			employee = employeeService.getEmployeeMessage(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return employee;
	}
	
	
	public Employee selectEmployeeByEmpNoAndName(Integer empNo, String name)
	throws Exception {
		trans.begin();
		Employee employee ;
		try {
			employee = employeeService.selectEmployeeByEmpNoAndName(empNo, name);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return employee;
	}
	
	public List<Employee> selectOneNo(String name) throws Exception {
		trans.begin();
		List<Employee> list = null;
		try {
			list = employeeService.selectOneNo(name);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	public Employee selectEmployeeNoByName(String name) throws Exception {
		trans.begin();
		Employee employee ;
		try {
			employee = employeeService.selectEmployeeNoByName(name);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return employee;
	}
	
	
	public Employee selectEmployeeNameByEmpNo(Integer empNo) throws Exception {
		trans.begin();
		Employee employee ;
		try {
			employee = employeeService.selectEmployeeNameByEmpNo(empNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return employee;
	}
	
	public void updateEmployeeDeptNameByDeptName(String oldName, String newName)
	throws Exception {
		trans.begin();
		try {
			employeeService.updateEmployeeDeptNameByDeptName(oldName, newName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}
	
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public Transaction getTrans() {
		return trans;
	}
	public void setTrans(Transaction trans) {
		this.trans = trans;
	}




}
