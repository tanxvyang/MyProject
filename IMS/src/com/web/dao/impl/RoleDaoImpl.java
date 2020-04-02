package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.RoleDao;
import com.web.mapper.AccountMapper;
import com.web.mapper.DeptMapper;
import com.web.mapper.RoleMapper;
import com.web.mapper.RowMapper;
import com.web.pojo.Role;
import com.web.util.JDBCTemplate;

public class RoleDaoImpl implements RoleDao{
	JDBCTemplate<Role> temp = new JDBCTemplate<Role>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	
	public Integer countUser(String role) {
		StringBuffer sb = new StringBuffer()
			.append(" select ")
			.append(" 	count(id) nums ")
			.append(" from ")
			.append(" 	t_role ")
			.append(" where ")
			.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		if(role != null && !role.equals("")) {
			sb.append(" and t_role_name = ? ");
			param.add(role);
		}
		return tempCount.selectOne(new RowMapper<Integer>() {
			public Integer mapperObject(ResultSet rs) throws Exception {
				return rs.getInt("nums");
			}
		}, sb.toString(), param.toArray());
	}

	public List<Role> selectUserByPage(String role, Integer pageNo,
			Integer pageSize) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_role_name,t_create_time ")
		.append(" from ")
		.append(" 	t_role ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		if(role != null && !role.equals("")) {
			sb.append(" and t_role_name = ? ");
			param.add(role);
		}
		sb.append(" limit ")
		  .append(" 	?,? ");
		param.add((pageNo - 1) * pageSize);
		param.add(pageSize);
	
	return temp.selectAll(new RoleMapper(), sb.toString(), param.toArray() );
	}

	public void addRole(String roleName) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_role(id,t_role_name,t_create_time) ")
		.append(" values ")
		.append(" 	(?,?,now()) ")
		.toString();
	temp.insert(sql, null,roleName);
	}

	public Role selectRoleByName(String roleName) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_role_name,t_create_time ")
		.append(" from ")
		.append(" 	t_role ")
		.append(" where ")
		.append("   t_role_name = ?  ")
		.toString();
		return temp.selectOne(new RoleMapper(),sql,roleName);
	}

	public void deleteRoleById(Integer id) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_role ")
		.append(" where ")
		.append(" 	 id  = ? ")
		.toString();
		temp.delete(sql, id);
	}

	public Role selectRoleNameById(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_role_name,t_create_time ")
		.append(" from ")
		.append(" 	t_role ")
		.append(" where ")
		.append("   id = ?  ")
		.toString();
		return temp.selectOne(new RoleMapper(),sql,id);
	}

	public List<Role> selectRole() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_role_name,t_create_time ")
		.append(" from ")
		.append(" 	t_role ")
		.toString();
		return temp.selectAll(new RoleMapper(), sql);	
	}

	public void updateRole(Integer id, String roleName) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_role ")
		.append(" set ")
		.append(" 	t_role_name = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
	temp.update(sql, roleName,id);
	}

}
