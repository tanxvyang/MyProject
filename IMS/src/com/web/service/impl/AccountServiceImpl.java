package com.web.service.impl;

import java.util.List;

import com.web.dao.AccountDao;
import com.web.pojo.Account;
import com.web.pojo.Employee;
import com.web.pojo.Role;
import com.web.service.AccountService;
import com.web.util.Constants;
import com.web.util.Pager;

public class AccountServiceImpl implements AccountService{
	private AccountDao accountDao;
	
	public Pager<Account> selectAll(Integer pageNo, String empNo,
			String status, String role) throws Exception {
		Pager<Account> pager = new Pager<Account>();
		pager.setPageNo(pageNo);
		Integer totalCount = accountDao.countUser(empNo, status, role);
		if(totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_4);
		List<Account> users = accountDao.selectUserByPage(empNo, status, role,pageNo,Constants.PAGE_SIZE_4);
		pager.setList(users);
		return pager;
	}
	//添加账户
	public void addAccount(String num, String name, String sel1, String sel2)
	throws Exception {
		try {
			Account account = accountDao.selectAccountByName(name);
			if(account == null || account.equals("")){
				accountDao.addAccount(num, name, sel1, sel2);
			}else{
				throw new Exception(" 添加失败：不能重复添加");
			}
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("添加失败！"+e.getMessage());
		}
	}
	//删除账户
	public void removeAccount(Integer id) throws Exception {
		accountDao.removeAccount(id);
	}
	
	
	public List<Account> selectOneAccount(Integer id) throws Exception {
		List<Account> list = accountDao.selectOneAccount(id);
		return list;
	}
	
	//修改账户
	public void updateAccount(Integer onlyId, String status, String role)
	throws Exception {
		accountDao.updateAccount(onlyId, status, role);
	}
	
	
	public Account selectAccountNameByEmpNo(Integer id) throws Exception {
		Account account = accountDao.selectAccountNameByEmpNo(id);
		return account;
	}
	
	public List<Account> selectRole() throws Exception {
		 List<Account> list = accountDao.selectRole();
		return list;
	}
	
	public Account selectAccountRoleByName(String name) throws Exception {
		Account account = accountDao.selectAccountByName(name);
		return account;
	}
	
	
	//get,set方法
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}



}
