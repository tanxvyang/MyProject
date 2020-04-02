package com.web.dao.impl;

import com.web.dao.UserDao;
import com.web.mapper.UserMapper;
import com.web.pojo.User;
import com.web.util.JDBCTemplate;

public class UserDaoImpl implements UserDao{
	JDBCTemplate<User> temp = new JDBCTemplate<User>();
	public User selectUserByQQAndPassword(String empNo, String password) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	t_id,t_user_account,t_user_pwd,t_emp_no,t_emp_na,t_role_id,t_create_time ")
			.append(" from ")
			.append(" 	t_user ")
			.append(" where ")
			.append(" 	t_emp_no = ? and ")
			.append(" 	t_user_pwd = ?  ")
			.toString();
	return temp.selectOne(new UserMapper(), sql,empNo,password);	
	
}
	public void addUser(String num,Integer no, String name,
			Integer roleId) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_user(t_id,t_user_account,t_user_pwd,t_emp_no,t_emp_na,t_role_id,t_create_time) ")
		.append(" values ")
		.append(" 	(?,?,'admin',?,?,?,now()) ")
		.toString();
	temp.insert(sql, null,num,no,name,roleId);
		
	}
	public void deleteUser(String name) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_user ")
		.append(" where ")
		.append(" 	 t_emp_na  = ? ")
		.toString();
		temp.delete(sql, name);
		
	}
	public void updateUserRoleByName(String username, Integer roleId) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_user ")
		.append(" set ")
		.append(" 	t_role_id = ? ")
		.append(" where ")
		.append(" 	t_emp_na = ? ")
		.toString();
	temp.update(sql, roleId,username);
	}
	public User selectPwdByEmpNo(Integer empNo) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	t_id,t_user_account,t_user_pwd,t_emp_no,t_emp_na,t_role_id,t_create_time ")
			.append(" from ")
			.append(" 	t_user ")
			.append(" where ")
			.append(" 	t_emp_no = ?  ")
			.toString();
		return temp.selectOne(new UserMapper(), sql,empNo);
	}
	public void updateUserPwd(Integer empNo, String pwd) {
		String sql = new StringBuffer()
			.append(" update ")
			.append(" 	t_user ")
			.append(" set ")
			.append(" 	t_user_pwd = ? ")
			.append(" where ")
			.append(" 	t_emp_no = ? ")
			.toString();
		temp.update(sql, pwd,empNo);
	}
}