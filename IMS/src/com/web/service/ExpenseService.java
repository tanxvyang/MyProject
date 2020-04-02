package com.web.service;

import com.web.pojo.Dept;
import com.web.pojo.Expense;
import com.web.util.Pager;

public interface ExpenseService {
	public Pager<Expense> selectAll(Integer pageNo, Integer empId,String type, String expStatus,Integer a) throws Exception ;
	public Expense getExpenseMessage(String expNo) throws Exception;
	public void removeExpense(String expNo) throws Exception;
	public void  addExpense(Expense expense)throws Exception;
	//12-14 修改报销
	public void updateExpense(Expense expense) throws Exception;
}
