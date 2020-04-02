package com.web.service;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.pojo.Role;
import com.web.util.Pager;

public interface MergeService {
	public Pager<Merge> selectAll(Integer pageNo, String menu,String role) throws Exception ;
	
	public List<Menu> selectMenu()throws Exception;
	
	public void deletePermissionsById(Integer id)throws Exception;
	
	public void addPermissions(Integer roleId,Integer menuId)throws Exception;
	
	public Menu selectMenuIdByMenuName(String menuName)throws Exception;
	
	public void updatePermissions(Integer id,Integer roleId,Integer menuId)throws Exception;
	
	public List<Merge> selectMenu(String roleName)throws Exception;
}
