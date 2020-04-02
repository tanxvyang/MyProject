package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Permissions;

public class PermissionsMapper implements RowMapper<Permissions>{

	public Permissions mapperObject(ResultSet rs) throws Exception {
		Permissions permissions = new Permissions();
		permissions.setId(rs.getInt("id"));
		permissions.setId(rs.getInt("t_role_id"));
		permissions.setId(rs.getInt("t_menu_id"));
		permissions.setCreateDate(new Date( rs.getDate("t_create_time").getTime()));
		return permissions;
	}

}
