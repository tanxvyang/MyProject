package com.web.service;

import java.util.List;

import com.web.pojo.Role;
import com.web.util.Pager;

public interface RoleService {
	public Pager<Role> selectAll(Integer pageNo, String role) throws Exception ;
	
	public void addRole(String roleName)throws Exception;
	
	public void deleteRoleById(Integer id)throws Exception;
	
	public List<Role> selectRole()throws Exception;
	
	public void updateRoleName(Integer id,String roleName)throws Exception;
	
	public Role selectRoleIdByRoleName(String roleName)throws Exception;
}