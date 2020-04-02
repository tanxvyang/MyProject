package com.web.dao;

import java.util.List;

import com.web.pojo.Dept;
import com.web.pojo.Employee;
import com.web.pojo.Expense;

public interface ExpenseDao {
	public List<Expense> selectExpenseByPage( Integer empId,String type, String expStatus, Integer pageNo, Integer pageSize,Integer a);
	public List<Expense> selectAllExpense();
	public Integer countExpense( String type, String expStatus ,Integer empId,Integer a);
	public void insertExpense(Expense expense);
	public void updateExpense(Expense expense);
	public void deleteExpense(Integer expNo );
	public Expense selectExpenseByexpNo(Integer expNo);
	public void removeExpenseByName(Integer empNo);

}
