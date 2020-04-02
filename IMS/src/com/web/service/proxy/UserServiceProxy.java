package com.web.service.proxy;

import com.web.pojo.User;
import com.web.service.UserService;
import com.web.trans.Transaction;

public class UserServiceProxy implements UserService{
	private UserService userService;
	private Transaction trans;
	public User login(String empNo, String password) throws Exception {
		trans.begin();
		User user;
		try {
			user = userService.login(empNo, password);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return user;
	}
	public void addUser(String num, Integer no, String name,Integer roleId) throws Exception {
		trans.begin();
		try {
			userService.addUser(num, no, name, roleId);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	public void deleteUser(String name) throws Exception {
		trans.begin();
		try {
			userService.deleteUser(name);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}
	public void updateUserRoleByName(String username, Integer roleId)
	throws Exception {
		trans.begin();
		try {
			userService.updateUserRoleByName(username, roleId);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	public void updateUserPwd(Integer empNo, String oldPwd, String newPwd)
			throws Exception {
		trans.begin();
		try {
			userService.updateUserPwd(empNo, oldPwd, newPwd);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
	}
	
	public User selectUserByEmpNo(Integer empNo) throws Exception {
		trans.begin();
		User user;
		try {
			user = userService.selectUserByEmpNo(empNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}



}
