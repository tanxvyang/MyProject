package com.web.service;

import java.util.List;

import com.web.pojo.Account;
import com.web.pojo.Employee;
import com.web.pojo.Role;
import com.web.util.Pager;

public interface AccountService {
	public Pager<Account> selectAll(Integer pageNo, String empNo, String status, String role) throws Exception ;

	public void removeAccount(Integer id)throws Exception;
	
	public void addAccount(String num, String name, String sel1, String sel2)throws Exception;

	public List<Account> selectOneAccount(Integer id) throws Exception;
	
	public void updateAccount(Integer onlyId,String status,String role)throws Exception;
	
	public Account selectAccountNameByEmpNo(Integer id)throws Exception;
	
	public List<Account> selectRole()throws Exception;
	
	public Account selectAccountRoleByName(String name)throws Exception;
}
