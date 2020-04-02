package com.web.service.proxy;

import com.web.pojo.Dept;
import com.web.pojo.Expense;
import com.web.service.ExpenseService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class ExpenseServiceProxy implements ExpenseService {
	private ExpenseService expenseService;
	private Transaction trans;
	public ExpenseService getExpenseService() {
		return expenseService;
	}
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	public Transaction getTrans() {
		return trans;
	}
	public void setTrans(Transaction trans) {
		this.trans = trans;
	}
	
	
	public Pager<Expense> selectAll(Integer pageNo, Integer empId, String type,
			String expStatus,Integer a) throws Exception {
		trans.begin();
		Pager<Expense> pager;
		try {
			pager = expenseService.selectAll( pageNo,  empId,  type, expStatus,a);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return pager;
	}
	public Expense getExpenseMessage(String expNo) throws Exception {
		trans.begin();
		Expense expense ;
		try {
			expense = expenseService.getExpenseMessage(expNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return expense;
	}
	public void removeExpense(String expNo) throws Exception {
		trans.begin();
		try {
			expenseService.removeExpense(expNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}
	public void addExpense(Expense expense) throws Exception {
		trans.begin();
		try {
			expenseService.addExpense(expense);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}
	public void updateExpense(Expense expense) throws Exception {
		trans.begin();
		try {
			expenseService.updateExpense(expense);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}

}