package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Dept;
import com.web.service.DeptService;
import com.web.service.EmployeeService;
import com.web.util.Pager;

public class DeptAction {
	private DeptService deptService;
	private EmployeeService employeeService;
	
	/**
	 * @author tan
	 * 部门明细
	 * @param request
	 * @param response
	 * @return
	 */
	public String getDeptMessage(HttpServletRequest request,HttpServletResponse response){
		String deptNo =  request.getParameter("deptNo");
		try {
			Dept dept =  deptService.getDeptMessage(deptNo);
			PrintWriter out;
			out = response.getWriter();
			String json = JSONObject.fromObject(dept).toString();
			out.write(json);
			out.flush();
			out.close();
			return "success";
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
		
	}
	
	public String removeDept(HttpServletRequest request,HttpServletResponse response){
		
		String deptNo =  request.getParameter("deptNo");
		PrintWriter out;
		try {
			deptService.removeDept(deptNo);
			out = response.getWriter();
			//String json = JSONObject.fromObject(true).toString();
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
			
//			out.write("{isError:"+true+",errorMessage:"+e.getMessage()+"}");
//			out.flush();
//			out.close();
		}
		return "success";
		
	}
	
	public String addDept(HttpServletRequest request,HttpServletResponse response){
		String deptNo =  request.getParameter("deptNo");
		String deptName =  request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		String deptManager=  request.getParameter("deptMaster");
		Dept dept = new Dept();
		dept.setDeptNo(deptNo);
		dept.setDeptName(deptName);
		dept.setDeptLoc(deptLoc);
		dept.setDeptManager(deptManager);
		try {
			deptService.addDept(dept);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
	}
	
	/**
	 * tian
	 * 12-12 更改部门信息 
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateDept(HttpServletRequest request,HttpServletResponse response){
		String deptNo =  request.getParameter("deptNo");
		String deptName =  request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		String deptManager=  request.getParameter("deptMaster");
		Dept dept = new Dept();
		dept.setDeptNo(deptNo);
		dept.setDeptName(deptName);
		dept.setDeptLoc(deptLoc);
		dept.setDeptManager(deptManager);
		try {
			String oldName = deptService.selectDeptNameByDeptNo(deptNo).getDeptName();
			employeeService.updateEmployeeDeptNameByDeptName(oldName, deptName);
			deptService.updateDept(dept);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
	}

	
	public String getUserByPage(HttpServletRequest request,HttpServletResponse response) {
//		 String deptNo;
//		 String deptName;
//		 String deptLoc;
//		 String deptManager;
//		 Date createDate;
//		
		//	
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			Pager<Dept> pager = deptService.getDeptByPage(pageNo);
			PrintWriter out;
			out = response.getWriter();
			String json = JSONObject.fromObject(pager).toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			
		} catch (Exception e) {
		
		}
		return "success";
	}
	
	
	
	/**
	 * 下拉框查询部门
	 * @param request
	 * @param response
	 * @return
	 */
	public String selectDept(HttpServletRequest request,HttpServletResponse response){
		try {
			List<Dept> list = deptService.selectAllDept();
			PrintWriter out;
			out = response.getWriter();
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
	
	
	
	
	
	
	
	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	
}
