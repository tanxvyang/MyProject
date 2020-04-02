package com.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;
import com.web.dao.MergeDao;
import com.web.mapper.MenuMapper;
import com.web.mapper.MergeMapper;
import com.web.mapper.PermissionsMapper;
import com.web.mapper.RoleMapper;
import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.pojo.Permissions;
import com.web.util.JDBCTemplate;

public class MergeDaoImpl implements MergeDao{
	JDBCTemplate<Merge> temp = new JDBCTemplate<Merge>();
	JDBCTemplate<Menu> temp2 = new JDBCTemplate<Menu>();
	JDBCTemplate<Permissions> temp3 = new JDBCTemplate<Permissions>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	
	
	public List<Merge> selectUserByPage(String menu, String role,
			Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	p.id,p.t_role_id,r.t_role_name,p.t_menu_id,m.t_menu_nam ")
		.append(" from ")
		.append(" 	t_role r,t_permissions p,t_menu m  ")
		.append(" where ")
		.append(" 	p.t_role_id = r.id and t_menu_id = m.id ");
		List<Object> param = new ArrayList<Object>();
		if(menu != null && !menu.equals("")) {
			sb.append(" and m.t_menu_nam = ? ");
			param.add(menu);
		}
		if(role != null && !role.equals("")) {
			sb.append(" and r.t_role_name = ? ");
			param.add(role);
		}
		sb.append(" limit ")
		  .append(" 	?,? ");
		param.add((pageNo - 1) * pageSize);
		param.add(pageSize);
	
	return temp.selectAll(new MergeMapper(), sb.toString(), param.toArray() );
	}


	public List<Menu> selectMenu() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_menu_nam,t_create_time ")
		.append(" from ")
		.append(" 	t_menu ")
		.toString();
		return temp2.selectAll(new MenuMapper(), sql);	
	}


	public void deletePermissionsById(Integer id) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_permissions ")
		.append(" where ")
		.append(" 	 id  = ? ")
		.toString();
		temp.delete(sql, id);
	}


	public void addPermissions(Integer roleId, Integer menuId) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_permissions(id,t_role_id, t_menu_id ,t_create_time) ")
		.append(" values ")
		.append(" 	(?,?,?,now()) ")
		.toString();
	temp.insert(sql, null,roleId,menuId);
	}


	public Menu selectMenuIdByMenuName(String Menu) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_menu_nam,t_create_time ")
		.append(" from ")
		.append(" 	t_menu ")
		.append(" where ")
		.append(" 	t_menu_nam = ? ")
		.toString();
		return temp2.selectOne(new MenuMapper(), sql,Menu);	
	}


	public void updatePermissions(Integer id, Integer roleId, Integer menuId) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_permissions ")
		.append(" set ")
		.append(" 	t_role_id = ?,t_menu_id = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
	temp.update(sql, roleId,menuId,id);		
	}


	public Permissions selectPermissionsIsNull(Integer roleId, Integer menuId) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_role_id,t_menu_id,t_create_time ")
		.append(" from ")
		.append(" 	t_permissions ")
		.append(" where ")
		.append(" 	t_role_id = ? and ")
		.append(" 	t_menu_id = ?  ")
		.toString();
		return temp3.selectOne(new PermissionsMapper(), sql,roleId,menuId);	
	}


	public List<Merge> selectMenu(String roleName) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	p.id,p.t_role_id,r.t_role_name,p.t_menu_id,m.t_menu_nam ")
		.append(" from ")
		.append(" 	t_role r,t_permissions p,t_menu m  ")
		.append(" where ")
		.append(" 	p.t_role_id = r.id and t_menu_id = m.id and t_role_name = ? ")
		.toString();
		return temp.selectAll(new MergeMapper(), sql,roleName);
	}
}
