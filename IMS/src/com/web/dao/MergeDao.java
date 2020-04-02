package com.web.dao;

import java.util.List;

import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.pojo.Permissions;
import com.web.pojo.Role;

public interface MergeDao {
	public List<Merge> selectUserByPage( String menu,String role, Integer pageNo, Integer pageSize);

	public  List<Menu> selectMenu();
	
	public void deletePermissionsById(Integer id);
	
	public Menu selectMenuIdByMenuName(String Menu);
	
	public void addPermissions(Integer roleId,Integer menuId);
	
	public void updatePermissions(Integer id,Integer roleId,Integer menuId);
	
	public Permissions selectPermissionsIsNull(Integer roleId,Integer menuId);
	
	public List<Merge> selectMenu(String roleName);
}
