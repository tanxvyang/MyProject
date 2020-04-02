package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.Merge;

public class MergeMapper  implements RowMapper<Merge>{

	public Merge mapperObject(ResultSet rs) throws Exception {
		Merge merge = new Merge();
		merge.setId(rs.getInt("id"));
		merge.setRoleId(rs.getInt("t_role_id"));
		merge.setRoleName(rs.getString("t_role_name"));
		merge.setMenuId(rs.getInt("t_menu_id"));
		merge.setMenuName(rs.getString("t_menu_nam"));
		return merge;
	}

}
