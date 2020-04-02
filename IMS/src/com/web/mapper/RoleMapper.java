package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.Role;

public class RoleMapper implements RowMapper<Role>{

	public Role mapperObject(ResultSet rs) throws Exception {
		Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setRoleName(rs.getString("t_role_name"));
		role.setCreateTime(rs.getString("t_create_time"));
		return role;
	}

}
