package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Account;
import com.web.pojo.Employee;
import com.web.pojo.Role;
import com.web.service.AccountService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class AccountServiceProxy implements AccountService{
	private Transaction trans;
	private AccountService accountService;
	
	public Pager<Account> selectAll(Integer pageNo, String empNo,
			String status, String role) throws Exception {
		trans.begin();
		Pager<Account> Pager;
		try {
			Pager = accountService.selectAll(pageNo,empNo,status,role);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return Pager;
	}

	public void removeAccount(Integer id) throws Exception {
		trans.begin();
		try {
			accountService.removeAccount(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
	}
	
	public void addAccount(String num, String name, String sel1, String sel2)
	throws Exception {
		trans.begin();
		try {
			accountService.addAccount(num, name, sel1, sel2);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	
	public List<Account> selectOneAccount(Integer id) throws Exception {
		trans.begin();
		List<Account> list = null;
		try {
			list = accountService.selectOneAccount(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	
	public void updateAccount(Integer onlyId, String status, String role)
	throws Exception {
		trans.begin();
		try {
			accountService.updateAccount(onlyId, status, role);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	
	public Account selectAccountNameByEmpNo(Integer id) throws Exception {
		trans.begin();
		Account account ;
		try {
			account = accountService.selectAccountNameByEmpNo(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return account;
	}
	public List<Account> selectRole() throws Exception {
		trans.begin();
		List<Account> list = null;
		try {
			list = accountService.selectRole();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	public Account selectAccountRoleByName(String name) throws Exception {
		trans.begin();
		Account account ;
		try {
			account = accountService.selectAccountRoleByName(name);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return account;
	}
	
	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}










}
