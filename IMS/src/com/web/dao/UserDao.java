package com.web.dao;

import com.web.pojo.User;

public interface UserDao {
	public User selectUserByQQAndPassword(String empNo,String password);
	
	public void addUser(String num,Integer no,String name,Integer roleId);
	
	public void deleteUser(String name);
	
	public void updateUserRoleByName(String username,Integer roleId);
	
	public User selectPwdByEmpNo(Integer empNo);
	
	public void updateUserPwd(Integer empNo,String pwd);
}
