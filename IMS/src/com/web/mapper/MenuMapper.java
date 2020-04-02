package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.Menu;

public class MenuMapper implements RowMapper<Menu>{

	public Menu mapperObject(ResultSet rs) throws Exception {
		Menu menu = new Menu();
		menu.setId(rs.getInt("id"));
		menu.setMenuName(rs.getString("t_menu_nam"));
		menu.setCreateTime(rs.getString("t_create_time"));
		return menu;
	}

}
