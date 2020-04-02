package com.web.service;

import com.web.pojo.User;

public interface UserService {
	public User login(String empNo,String password) throws Exception;
	
	public void addUser(String num,Integer no,String name,Integer roleId) throws Exception;
	
	public void deleteUser(String name)throws Exception;
	
	public void updateUserRoleByName(String username, Integer roleId)throws Exception;
	
	public void updateUserPwd(Integer empNo,String oldPwd,String newPwd)throws Exception;
	
	public User selectUserByEmpNo(Integer empNo)throws Exception;
}
