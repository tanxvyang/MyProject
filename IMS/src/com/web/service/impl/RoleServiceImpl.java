package com.web.service.impl;

import java.util.List;

import com.web.dao.AccountDao;
import com.web.dao.RoleDao;
import com.web.pojo.Account;
import com.web.pojo.Role;
import com.web.service.RoleService;
import com.web.util.Constants;
import com.web.util.Pager;

public class RoleServiceImpl implements RoleService{
	private RoleDao roleDao;
	private AccountDao accountDao;
	
	public Pager<Role> selectAll(Integer pageNo, String role) throws Exception {
		Pager<Role> pager = new Pager<Role>();
		pager.setPageNo(pageNo);
		Integer totalCount = roleDao.countUser(role);
		if(totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_2);
		List<Role> users = roleDao.selectUserByPage(role,pageNo,Constants.PAGE_SIZE_2);
		pager.setList(users);
		return pager;
	}
	
	//添加角色名  且角色名不能重复添加
	public void addRole(String roleName) throws Exception {
		try {
			Role role = roleDao.selectRoleByName(roleName);
			if(role == null || role.equals("")){
				roleDao.addRole(roleName);
			}else{
				throw new Exception(" 添加失败：不能重复添加");
			}
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("添加失败！"+e.getMessage());
		}		
	}
	
	//删除角色名  且角色名下有员工不能删除
	public void deleteRoleById(Integer id) throws Exception {
		try {
			String roleName = roleDao.selectRoleNameById(id).getRoleName();
			Account account = accountDao.selectAccountByRoleName(roleName);
			if(account == null || account.equals("")){
				roleDao.deleteRoleById(id);
			}else{
				throw new Exception(" 删除失败：该角色名下有员工");
			}
		} catch (Exception e) {
			throw e;
		}		
	}
	
	
	public List<Role> selectRole() throws Exception {
		 List<Role> list = roleDao.selectRole();
		return list;
	}
	
	
	//修改角色名
	public void updateRoleName(Integer id, String roleName) throws Exception {
		String oldName = roleDao.selectRoleNameById(id).getRoleName();
		Role role = roleDao.selectRoleByName(oldName);
		if(role == null || role.equals("")){
			accountDao.updateAccountNewByOle(roleName, oldName);
			roleDao.updateRole(id, roleName);
		}else{
			throw new Exception(" 添加失败：不能重复添加");
		}
	}
	
	public Role selectRoleIdByRoleName(String roleName) throws Exception {
		Role role = roleDao.selectRoleByName(roleName);
		return role;
	}
	
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}





	

}
