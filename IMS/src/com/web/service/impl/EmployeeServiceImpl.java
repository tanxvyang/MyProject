package com.web.service.impl;

import java.util.List;

import com.web.dao.AccountDao;
import com.web.dao.DeptDao;
import com.web.dao.EmployeeDao;
import com.web.dao.ExpenseDao;
import com.web.dao.HolidayDao;
import com.web.dao.UserDao;
import com.web.pojo.Dept;
import com.web.pojo.Employee;
import com.web.service.EmployeeService;
import com.web.util.Constants;
import com.web.util.Pager;

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDao employeeDao;
	private DeptDao deptDao;
	private HolidayDao holidayDao;
	private ExpenseDao expenseDao;
	private AccountDao accountDao;
	private UserDao userDao;
	
	public Pager<Employee> selectAll(Integer pageNo, String empNo,
			String name, String dapt) {
		Pager<Employee> pager = new Pager<Employee>();
		pager.setPageNo(pageNo);
		Integer totalCount = employeeDao.countUser(empNo, name, dapt);
		if(totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_4);
		List<Employee> users = employeeDao.selectUserByPage(empNo, name, dapt,pageNo,Constants.PAGE_SIZE_4);
		pager.setList(users);
		return pager;
	}
	
	public void addEmployee(Integer No, String name, String sel1, String sel2,
			String time) throws Exception {
		Employee employee = employeeDao.selectEmployeeByEmpNo(No);
		Employee employeeName = employeeDao.selectEmployeeByname(name);
		
		try {
			if((employee == null || employee.equals("")) && (employeeName == null || employeeName.equals(""))){
				employeeDao.addEmployee(No, name, sel1, sel2, time);
			}else{
				throw new Exception(" 添加失败：员工编号或者姓名重复");
			}
			
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("添加失败！"+e.getMessage());
		}
		
		
	}

	
	
	
	public List<Employee> selectOneEmployee(Integer id) throws Exception {
		List<Employee> list = employeeDao.selectOneEmployee(id);
		return list;
	}
	
	public void updateEmployee(Integer id,
			String sex, String dept, String entryTime) throws Exception {
		employeeDao.updateEmployee(id, sex, dept, entryTime);
		
	}
	public Employee getEmployeeMessage(Integer id) throws Exception {
		Employee employee = employeeDao.getEmployeeMessage(id);
		if (employee == null || employee.equals("")) {
			throw new Exception(" 该员工不存在或已被删除！ ");
		}else{
			return employee;
		}
	}
	
	public void removeEmployee(Integer id) throws Exception {
		String name = employeeDao.getEmployeeMessage(id).getName();
		Dept dept =  deptDao.selectDeptByName(name);
		Integer empNo = employeeDao.selectEmployeeByname(name).getEmpNo();
		try {
			if(dept == null || dept.equals("")){
				employeeDao.removeEmployee(id);
				holidayDao.removeHolidayByName(name);
				expenseDao.removeExpenseByName(empNo);
				accountDao.removeAccountByName(name);
				userDao.deleteUser(name);
			}else{
				throw new Exception("该员工为部门经理，无法删除");
			}
			
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("删除失败！"+e.getMessage());
		}
	}
	
	
	

	//为增加账户查询用户编码是否存在
	public Employee selectEmployeeByEmpNoAndName(Integer empNo, String name)
	throws Exception {
		Employee employee =  employeeDao.selectEmployeeByEmpNoAndName(empNo, name);
		return employee;
	}
	
	public List<Employee> selectOneNo(String name) throws Exception {
		List<Employee> list = employeeDao.selectOneNo(name);
		return list;
	}
	
	//通过名字查用户编号
	public Employee selectEmployeeNoByName(String name) throws Exception {
		Employee employee = employeeDao.selectEmpByName(name);
		return employee;
	}
	
	
	public Employee selectEmployeeNameByEmpNo(Integer empNo) throws Exception {
		Employee employee = employeeDao.selectEmployeeByEmpNo(empNo);
		return employee;
	}

	
	
	public void updateEmployeeDeptNameByDeptName(String oldName, String newName)
	throws Exception {
		employeeDao.updateEmployeeDeptNameByDeptName(oldName, newName);
	}
	
	
	
	
	
	public HolidayDao getHolidayDao() {
		return holidayDao;
	}

	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}

	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}








}
