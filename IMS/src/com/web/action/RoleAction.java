package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Account;
import com.web.pojo.Dept;
import com.web.pojo.Role;
import com.web.service.RoleService;
import com.web.util.Pager;

public class RoleAction {
	private RoleService roleService;

	public String roleAll(HttpServletRequest request, HttpServletResponse resp){
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String role = request.getParameter("role");
		try {
			Pager<Role> pager = roleService.selectAll(pageNo,role);
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
	
	//增加角色名
	public String addRole(HttpServletRequest request, HttpServletResponse resp){
		String roleName =request.getParameter("roleName");
		try {
			roleService.addRole(roleName);
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	//删除角色
	public String removeRole(HttpServletRequest request, HttpServletResponse resp){
		Integer deptNo = Integer.valueOf( request.getParameter("deptNo"));
		PrintWriter out;
		try {
			roleService.deleteRoleById(deptNo);
			out = resp.getWriter();
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
			
		}
		return "success";
	}
	
	//为下拉框查询所有角色名
	public String selectRole(HttpServletRequest request, HttpServletResponse resp){
		try {
			List<Role> list = roleService.selectRole();
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
	
	//修改角色名
	public String updateRole(HttpServletRequest request, HttpServletResponse resp){
		Integer onlyId = Integer.valueOf( request.getParameter("onlyId"));
		String roleName =request.getParameter("roleName");
		try {
			roleService.updateRoleName(onlyId, roleName);
			return "success";
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}
}
