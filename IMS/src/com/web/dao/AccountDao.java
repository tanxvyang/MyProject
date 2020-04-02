package com.web.dao;

import java.util.List;

import com.web.pojo.Account;
import com.web.pojo.Employee;
import com.web.pojo.Role;

public interface AccountDao {
	public Integer countUser(String empNo, String status, String role);
	
	public List<Account> selectUserByPage(String empNo, String status, String role, Integer pageNo, Integer pageSize);

	public void removeAccount(Integer id);
	
	public void addAccount(String num, String name, String sel1, String sel2);
	
	public Account selectAccountByName(String name);
	
	public List<Account> selectOneAccount(Integer id); 
	
	public void updateAccount(Integer onlyId,String status,String role);
	
	public Account selectAccountNameByEmpNo(Integer id);
	
	public Account selectAccountByRoleName(String name);
	
	public List<Account> selectRole();
	
	public void updateAccountNewByOle(String NewName,String OleName);
	
	public void removeAccountByName(String name);
	
}
