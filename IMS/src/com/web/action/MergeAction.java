package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.pojo.Role;
import com.web.service.AccountService;
import com.web.service.MergeService;
import com.web.service.RoleService;
import com.web.util.Pager;

public class MergeAction {
	private MergeService mergeService;
	private RoleService roleService;
	private AccountService accountService;
	
	public String selectAll(HttpServletRequest request, HttpServletResponse resp){
		
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String role = request.getParameter("role");
		String menu = request.getParameter("menu");
		
		try {
			Pager<Merge> pager = mergeService.selectAll(pageNo, menu, role);
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONObject.fromObject(pager).toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String selectMenu(HttpServletRequest request, HttpServletResponse resp){
		try {
			List<Menu> list = mergeService.selectMenu();
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONArray.fromObject(list).toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String removePermissions(HttpServletRequest request, HttpServletResponse resp){
		Integer deptNo = Integer.valueOf( request.getParameter("deptNo"));
		try {
			mergeService.deletePermissionsById(deptNo);
			PrintWriter out;
			out = resp.getWriter();
			out.flush();
			out.close();
		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("isError", true);
//			request.setAttribute("errorMessage", e.getMessage());
		}
		return "success";
	}
	
	public String addMerge(HttpServletRequest request, HttpServletResponse resp){
		String role = request.getParameter("role");
		String menu = request.getParameter("menu");
		try {
			Integer roleId = roleService.selectRoleIdByRoleName(role).getId();
			Integer menuId = mergeService.selectMenuIdByMenuName(menu).getId();
			mergeService.addPermissions(roleId, menuId);
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	//修改权限
	public String updateMerge(HttpServletRequest request, HttpServletResponse resp){
		Integer onlyId = Integer.valueOf( request.getParameter("onlyId"));
		String roleName = request.getParameter("roleName");
		String menuName = request.getParameter("menuName");
		try {
			Integer roleId = roleService.selectRoleIdByRoleName(roleName).getId();
			Integer menuId = mergeService.selectMenuIdByMenuName(menuName).getId();
			mergeService.updatePermissions(onlyId, roleId, menuId);
			return "success";
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}
	
	public String selectMerge(HttpServletRequest request, HttpServletResponse resp){
		String empNa = request.getParameter("empNa");
		try {
			String role = accountService.selectAccountRoleByName(empNa).getRole();
			List<Merge> list = mergeService.selectMenu(role);
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONArray.fromObject(list).toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public void setMergeService(MergeService mergeService) {
		this.mergeService = mergeService;
	}

	public MergeService getMergeService() {
		return mergeService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AccountService getAccountService() {
		return accountService;
	}
}
