package com.web.service.impl;

import java.util.List;
import com.web.dao.EmployeeDao;
import com.web.dao.ExpenseDao;
import com.web.pojo.Employee;
import com.web.pojo.Expense;
import com.web.service.ExpenseService;
import com.web.util.Constants;
import com.web.util.Pager;

public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseDao expenseDao;
	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	
	
	public Pager<Expense> selectAll(Integer pageNo, Integer empNo, String type,
			String expStatus,Integer a) throws Exception {
		Pager<Expense> pager = new Pager<Expense>();
		pager.setPageNo(pageNo);
		Integer totalCount = expenseDao.countExpense(type, expStatus, empNo,a);
		if(totalCount == null) {
			totalCount = 0;
		}
		
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_4);
		List<Expense> expenses = expenseDao.selectExpenseByPage(empNo, type, expStatus, pageNo,Constants.PAGE_SIZE_4,a);
		
		/**
		 * 更改了调用的dao层方法,如果出错,修改下面注释
		 */
		//Employee employee = employeeDao.selectEmpByEmpNo(empNo);
		//
		//System.out.println(employee);
		//
		
		if (expenses.size() != 0 ) {
			for (Expense expense : expenses) {
				//Employee employee = employeeDao.selectEmployeeByEmpNo(6238);
				Employee employee = employeeDao.selectEmployeeByEmpNo(expense.getEmpId());
				expense.setUsername(employee.getName());
			}
		}
		pager.setList(expenses);
		return pager;
	}

	public Expense getExpenseMessage(String expNo) throws Exception {
		System.out.println("ssss = "+Integer.valueOf(expNo.substring(2)));
		Expense expense = expenseDao.selectExpenseByexpNo( Integer.valueOf(expNo.substring(2)) );
		if (expense == null || expense.equals("")) {
			throw new Exception(" 该报销单不存在或已被删除！ ");
		}else{
			/**
			 * 更改了调用的dao层方法,如果出错,修改下面注释
			 */
			
		Employee employees = employeeDao.selectEmployeeByEmpNo(expense.getEmpId());
//		Employee employees = employeeDao.selectEmpByEmpNo(expense.getEmpId());
		if ( employees!= null) {
			expense.setUsername(employees.getName());
		}else{
			throw new Exception("该用户不存在");
		}
		}
		return expense;
	}
	
	
	

	public void removeExpense(String expNo) throws Exception {
		try {
			System.out.println("implexpNo=" +expNo);
		Expense expense = expenseDao.selectExpenseByexpNo( Integer.valueOf(expNo.substring(2)) );
				if (expense == null || expense.equals("")) {
					throw new Exception(" 该报销单不存在或已被删除！ ");
				}else{
					if (expense.getExpStatus() != "提交") {
					expenseDao.deleteExpense( Integer.valueOf(expNo.substring(2)) );
					}else{
						throw new Exception(" 该报销单已提交！");
					}
				}
				
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void  addExpense(Expense expense) throws Exception{
		try {
			
			if (expense.getType()!=null && expense.getExpStatus()!=null) {
				/**
				 * 更改了调用的dao层方法,如果出错,修改下面注释
				 */
				Employee employees = employeeDao.selectEmployeeByEmpNo(expense.getEmpId());
//				Employee employees = employeeDao.selectEmpByEmpNo(expense.getEmpId());
				if ( employees!= null) {
					expenseDao.insertExpense(expense);
				}else{
					throw new Exception("添加失败！当前用户状态异常");
				}
				
				
			}else{
				throw new Exception("添加失败！状态和类型均不能为空！");
			}
			
			
			
		} catch (Exception e) {
			throw new Exception("添加失败！");
		}
		
		
		
	}

	/**
	 * /12-14 修改报销
	 */
	public void updateExpense(Expense expense) throws Exception {
		try {

			if (expense.getType() != null && expense.getExpStatus() != null) {
				// 找到报销单
				Expense expenses = expenseDao.selectExpenseByexpNo(Integer
						.valueOf(expense.getExpNo().substring(2)));
				System.out.println("找到报销单"+expenses);
				if (expenses != null) {
					// 找到该用户,确认用户状态
					
					/**
					 * 更改了调用的dao层方法,如果出错,修改下面注释
					 */
					Employee employees = employeeDao.selectEmployeeByEmpNo(expenses.getEmpId());
					if (employees != null) {
						expenseDao.updateExpense(expense);
					} else {
						throw new Exception("修改失败！当前用户状态异常");
					}
				} else {
					throw new Exception("修改失败！未找到报销单！");
				}
			} else {
				throw new Exception("修改失败！状态和类型均不能为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
		
		
	}

}