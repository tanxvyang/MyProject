package com.web.dao;

import java.util.List;

import com.web.pojo.Role;

public interface RoleDao {
	
	public Integer countUser(String role);
	
	public List<Role> selectUserByPage( String role, Integer pageNo, Integer pageSize);
	
	public void addRole(String roleName);
	
	public Role selectRoleByName(String roleName);
	
	public Role selectRoleNameById(Integer id);
	
	public void deleteRoleById(Integer id);
	
	public List<Role> selectRole();
	
	public void updateRole(Integer id,String roleName);
	
}
