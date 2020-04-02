package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.pojo.Employee;
import com.web.service.EmployeeService;
import com.web.util.Pager;

public class EmployeeAction {
	private EmployeeService employeeService;

	public String selectAll(HttpServletRequest request, HttpServletResponse resp){
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String empNo =request.getParameter("empNo");
		String name = request.getParameter("name");
		String dapt = request.getParameter("dapt");
		try {
			Pager<Employee> pager = employeeService.selectAll(pageNo,empNo,name,dapt);
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
	
	public String addEmployee(HttpServletRequest request, HttpServletResponse resp){
		Integer No = Integer.parseInt( request.getParameter("No"));
		String name =request.getParameter("name");
		String sel1 = request.getParameter("sel1");
		String sel2 = request.getParameter("sel2");
		String time = request.getParameter("time");
		try {
			employeeService.addEmployee(No, name, sel1, sel2, time);
			return "success";
		}  catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}
	public String updateEmployee(HttpServletRequest request, HttpServletResponse resp){
		//String name =request.getParameter("name");
		String time =request.getParameter("time");
		String tdSex =request.getParameter("tdSex");
		String dapt =request.getParameter("dapt");
		//Integer num = Integer.valueOf(request.getParameter("num"));
		Integer id = Integer.valueOf(request.getParameter("empid"));
		try {
			employeeService.updateEmployee(id,tdSex, dapt, time);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		
	}
	
	public String selectOneEmployee(HttpServletRequest request, HttpServletResponse resp){
		Integer empid = Integer.parseInt( request.getParameter("empid"));
		try {
			List<Employee> list = employeeService.selectOneEmployee(empid);
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
	public String removeEmployee(HttpServletRequest request, HttpServletResponse resp){
		Integer deptNo = Integer.valueOf( request.getParameter("deptNo"));
		PrintWriter out;
		try {
			employeeService.removeEmployee(deptNo);
			out = resp.getWriter();
			//String json = JSONObject.fromObject(true).toString();
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("isError", true);
//			request.setAttribute("errorMessage", e.getMessage());
		}
		return "success";
	}
	public String getEmployeeMessage(HttpServletRequest request, HttpServletResponse resp){
		Integer deptNo = Integer.valueOf( request.getParameter("deptNo"));
		try {
			Employee list = employeeService.getEmployeeMessage(deptNo);
			PrintWriter out;
			out = resp.getWriter();
			String json = JSONObject.fromObject(list).toString();
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
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}
}
