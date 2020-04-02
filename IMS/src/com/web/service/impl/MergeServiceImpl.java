package com.web.service.impl;

import java.util.List;

import com.web.dao.MergeDao;
import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.pojo.Permissions;
import com.web.pojo.Role;
import com.web.service.MergeService;
import com.web.util.Constants;
import com.web.util.Pager;

public class MergeServiceImpl implements MergeService{
	private MergeDao mergeDao;
	
	
	
	public Pager<Merge> selectAll(Integer pageNo, String menu, String role)
			throws Exception {
		Pager<Merge> pager = new Pager<Merge>();
		pager.setPageNo(pageNo);
		pager.setTotalPage(1, Constants.PAGE_SIZE_100);
		List<Merge> users = mergeDao.selectUserByPage(menu,role,pageNo,Constants.PAGE_SIZE_100);
		pager.setList(users);
		return pager;
	}

	public List<Menu> selectMenu() throws Exception {
		 List<Menu> list = mergeDao.selectMenu();
			return list;
	}

	//删除权限
	public void deletePermissionsById(Integer id) throws Exception {
		mergeDao.deletePermissionsById(id);
	}
	
	//添加权限
	public void addPermissions(Integer roleId, Integer menuId) throws Exception {
		
		try {
			Permissions permissions = mergeDao.selectPermissionsIsNull(roleId, menuId);
			if(permissions == null || permissions.equals("")){
				mergeDao.addPermissions(roleId, menuId);
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
	
	
	public Menu selectMenuIdByMenuName(String menuName) throws Exception {
		Menu menu = mergeDao.selectMenuIdByMenuName(menuName);
		return menu;
	}
	
	
	//修改权限
	public void updatePermissions(Integer id, Integer roleId, Integer menuId)
	throws Exception {
		
		Permissions permissions = mergeDao.selectPermissionsIsNull(roleId, menuId);
		if(permissions == null || permissions.equals("")){
			mergeDao.updatePermissions(id, roleId, menuId);
		}else{
			throw new Exception(" 修改失败：与现有权限重复");
		}
		
	}
	
	//二级菜单
	public List<Merge> selectMenu(String roleName) throws Exception {
		 List<Merge> list = mergeDao.selectMenu(roleName);
		return list;
	}
	
	
	
	public void setMergeDao(MergeDao mergeDao) {
		this.mergeDao = mergeDao;
	}
	public MergeDao getMergeDao() {
		return mergeDao;
	}








}
