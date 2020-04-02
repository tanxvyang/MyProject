package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.pojo.Role;
import com.web.service.MergeService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class MergeServiceProxy implements MergeService{
	private Transaction trans;
	private MergeService mergeService;
	
	
	
	public Pager<Merge> selectAll(Integer pageNo, String menu, String role)
			throws Exception {
		trans.begin();
		Pager<Merge> Pager;
		try {
			Pager = mergeService.selectAll(pageNo,menu,role);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return Pager;
	}

	public List<Menu> selectMenu() throws Exception {
		trans.begin();
		List<Menu> list = null;
		try {
			list = mergeService.selectMenu();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	public void deletePermissionsById(Integer id) throws Exception {
		trans.begin();
		try {
			mergeService.deletePermissionsById(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
		
	}
	
	//添加权限
	public void addPermissions(Integer roleId, Integer menuId) throws Exception {
		trans.begin();
		try {
			mergeService.addPermissions(roleId, menuId);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
		
	}
	
	public Menu selectMenuIdByMenuName(String menuName) throws Exception {
		trans.begin();
		Menu menu ;
		try {
			menu = mergeService.selectMenuIdByMenuName(menuName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return menu;
	}
	
	//修改权限
	public void updatePermissions(Integer id, Integer roleId, Integer menuId)
	throws Exception {
		trans.begin();
		try {
			mergeService.updatePermissions(id, roleId, menuId);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}		
		
	}
	//二级菜单
	public List<Merge> selectMenu(String roleName) throws Exception {
		trans.begin();
		List<Merge> list = null;
		try {
			list = mergeService.selectMenu(roleName);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}
	
	
	
	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public MergeService getMergeService() {
		return mergeService;
	}

	public void setMergeService(MergeService mergeService) {
		this.mergeService = mergeService;
	}









	
}
