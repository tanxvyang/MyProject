package com.web.service.impl;

import com.web.dao.UserDao;
import com.web.pojo.Role;
import com.web.pojo.User;
import com.web.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	public User login(String empNo, String password) throws Exception {
		User user = null;
		try {
			user = userDao.selectUserByQQAndPassword(empNo, password);
			if(user == null) {
				throw new Exception("登录失败：用户不存在");
			}else{
				if(!user.getPassword().equals(password)) {
					throw new Exception("登录失败：用户名或密码错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("登录失败"+e.getMessage());
		}
		return user;
	}
	
	public void deleteUser(String name) throws Exception {
		userDao.deleteUser(name);
	}

	public void addUser(String num,Integer no, String name,Integer roleId) throws Exception {
		userDao.addUser(num, no, name, roleId);
	}

	
	public void updateUserRoleByName(String username, Integer roleId)
	throws Exception {
		userDao.updateUserRoleByName(username, roleId);
	}
	
	//修改密码
	public void updateUserPwd(Integer empNo, String oldPwd,String newPwd) throws Exception {
		try {
			String userPwd = userDao.selectPwdByEmpNo(empNo).getPassword();
			System.out.println("oldPwd = "+oldPwd);
			System.out.println("userPwd = "+userPwd);
			if(userPwd == oldPwd || userPwd.equals(oldPwd)){
				userDao.updateUserPwd(empNo, newPwd);
			}else{
				throw new Exception("修改失败：原密码填写错误");
			}
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("修改失败！"+e.getMessage());
		}		
	}
	
	
	
	
	public User selectUserByEmpNo(Integer empNo) throws Exception {
		User user = userDao.selectPwdByEmpNo(empNo);
		return user;
	}
	
	
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}







}
