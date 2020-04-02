package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Dept;

public class DeptMapper implements RowMapper<Dept> {

	public Dept mapperObject(ResultSet rs) throws Exception {
		Dept dept = new Dept();
		dept.setDeptNo(rs.getString("t_dept_no"));
		dept.setDeptName(rs.getString("t_dept_name"));
		dept.setDeptLoc(rs.getString("t_dept_loc"));
		dept.setDeptManager(rs.getString("t_dept_manager"));
		dept.setCreateDate(new Date( rs.getDate("t_create_time").getTime()));
		return dept;
	}

}
