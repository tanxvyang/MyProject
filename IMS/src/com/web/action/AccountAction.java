package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Account;
import com.web.pojo.Employee;
import com.web.pojo.Role;
import com.web.service.AccountService;
import com.web.service.EmployeeService;
import com.web.service.UserService;
import com.web.util.Pager;

public class AccountAction {
	private AccountService accountService;
	private EmployeeService employeeService;
	private UserService userService;
	
	public String selectAll(HttpServletRequest request, HttpServletResponse resp){
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String empNo =request.getParameter("empNo");
		String status = request.getParameter("status");
		String role = request.getParameter("role");
		try {
			Pager<Account> pager = accountService.selectAll(pageNo,empNo,status,role);
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
	
	public String removeAccount(HttpServletRequest request, HttpServletResponse resp){
		Integer deptNo = Integer.valueOf( request.getParameter("deptNo"));
		try {
			String username = accountService.selectAccountNameByEmpNo(deptNo).getName();
			userService.deleteUser(username);
			accountService.removeAccount(deptNo);
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

	public String addAccount(HttpServletRequest request, HttpServletResponse resp){
		Integer empNo = Integer.valueOf(request.getParameter("empNo"));
		
		String num = request.getParameter("num");
		String name =request.getParameter("name");
		String sel1 = request.getParameter("sel1");
		String sel2 = request.getParameter("sel2");
		Integer roleId = null;
		if(sel2 == "管理员" || sel2.equals("管理员")){
			roleId = 1;
		}
		if(sel2 == "普通用户" || sel2.equals("普通用户")){
			roleId = 2;
		}
		if(sel2 == "人事专员" || sel2.equals("人事专员")){
			roleId = 3;
		}
		
		try {
			Employee number = employeeService.selectEmployeeByEmpNoAndName(empNo, name);
			if(number == null || number.equals("")){
				throw new Exception("添加失败，用户姓名或编码填写错误或不匹配");
			}else{
				accountService.addAccount(num, name, sel1, sel2);
				Integer userNo = employeeService.selectEmployeeNoByName(name).getEmpNo();
				userService.addUser(num, userNo, name, roleId);
			}
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	public String selectOneAccount(HttpServletRequest request, HttpServletResponse resp){
		Integer empid = Integer.parseInt(request.getParameter("empid"));
		try {
			List<Account> list = accountService.selectOneAccount(empid);
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
	
	
	public String selectOneNo(HttpServletRequest request, HttpServletResponse resp){
		Integer empid = Integer.parseInt(request.getParameter("empid"));
		try {
			String name = accountService.selectAccountNameByEmpNo(empid).getName();
			List<Employee> list = employeeService.selectOneNo(name);
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONArray.fromObject(list).toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "success";
	}
	
	public String updateAccount(HttpServletRequest request, HttpServletResponse resp){
		Integer onlyId = Integer.valueOf( request.getParameter("onlyId"));
		String status =request.getParameter("status");
		String role =request.getParameter("role");
		
		Integer roleId = null;
		if(role == "管理员" || role.equals("管理员")){
			roleId = 1;
		}
		if(role == "普通用户" || role.equals("普通用户")){
			roleId = 2;
		}
		if(role == "人事专员" || role.equals("人事专员")){
			roleId = 3;
		}
		
		try {
			String username = accountService.selectAccountNameByEmpNo(onlyId).getName();
			userService.updateUserRoleByName(username, roleId);
			accountService.updateAccount(onlyId, status, role);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
	}
	
	public String selectAccount(HttpServletRequest request, HttpServletResponse resp){
		try {
			List<Account> list = accountService.selectRole();
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
	
	public String ShiFouYouInput(HttpServletRequest request, HttpServletResponse resp){
		String username =request.getParameter("username");
		try {
			String roleName = accountService.selectAccountRoleByName(username).getRole();
			PrintWriter out;
			out = resp.getWriter();
			out.write(roleName);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}
}
