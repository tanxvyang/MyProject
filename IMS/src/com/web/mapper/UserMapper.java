package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.User;

public class UserMapper implements RowMapper<User>{

	public User mapperObject(ResultSet rs) throws Exception {
		User user = new User();
		user.setTid(rs.getInt("t_id"));
		user.setAccount(rs.getString("t_user_account"));
		user.setPassword(rs.getString("t_user_pwd"));
		user.setEmpNo(rs.getString("t_emp_no"));
		user.setEmpNa(rs.getString("t_emp_na"));
		user.setRoleId(rs.getInt("t_role_id"));
		user.setCreateTime(rs.getString("t_create_time"));
		return user;
	}
	
}
