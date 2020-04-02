package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Account;
import com.web.pojo.Employee;
import com.web.pojo.Role;
import com.web.service.RoleService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class RoleServiceProxy implements RoleService{
	private Transaction trans;
	private RoleService roleService;
	
	public Pager<Role> selectAll(Integer pageNo, String role) throws Exception {
		trans.begin();
		Pager<Role> Pager;
		try {
			Pager = roleService.selectAll(pageNo,role);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return Pager;
	}
	
	public void addRole(String roleName) throws Exception {
		trans.begin();
		try {
			roleService.addRole(roleName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		
	}
	public void deleteRoleById(Integer id) throws Exception {
		try {
			roleService.deleteRoleById(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	
	public List<Role> selectRole() throws Exception {
		trans.begin();
		List<Role> list = null;
		try {
			list = roleService.selectRole();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	//修改角色名
	public void updateRoleName(Integer id, String roleName) throws Exception {
		trans.begin();
		try {
			roleService.updateRoleName(id, roleName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	
	public Role selectRoleIdByRoleName(String roleName) throws Exception {
		trans.begin();
		Role role ;
		try {
			role = roleService.selectRoleIdByRoleName(roleName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return role;
	}
	
	public Transaction getTrans() {
		return trans;
	}
	public void setTrans(Transaction trans) {
		this.trans = trans;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}






	
}
