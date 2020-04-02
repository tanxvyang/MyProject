package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.web.pojo.Expense;
import com.web.pojo.Menu;
import com.web.pojo.Merge;
import com.web.service.AccountService;
import com.web.service.EmployeeService;
import com.web.service.ExpenseService;
import com.web.service.MergeService;
import com.web.util.Pager;

public class ExpenseAction {
	private ExpenseService expenseService;
	private EmployeeService employeeService;
	private AccountService accountService;
	private MergeService mergeService;
	
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public MergeService getMergeService() {
		return mergeService;
	}

	public void setMergeService(MergeService mergeService) {
		this.mergeService = mergeService;
	}

	public ExpenseService getExpenseService() {
		return expenseService;
	}

	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	
	
	/**
	 * @author tan
	 * 报销单明细
	 * 
	 * 12-12 19:34
	 * @param request
	 * @param resp
	 * @return
	 */
	public String getExpenseMessage(HttpServletRequest request,HttpServletResponse response){
		String expNo =  request.getParameter("expNo");
		System.out.println("expaction-getMessage-expNo="+expNo);
		try {
			Expense expense =  expenseService.getExpenseMessage(expNo);
			System.out.println("expaction-getMessage-expense="+expense);
			PrintWriter out;
			out = response.getWriter();
			String json = JSONObject.fromObject(expense).toString();
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
	
	
		
	
	
	/**
	 * 获得所有报销单
	 * 
	 * 12-13 tan
	 * 
	 * 测试；用户部分完善后需要将其中的测试内容改成完整的逻辑顺序
	 * 
	 * 		
	 * @param request
	 * @param resp
	 * @return
	 */
	public String selectAll(HttpServletRequest request, HttpServletResponse resp){
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		Integer empNo =  Integer.valueOf(request.getParameter("empNo"));
		
		
		
		String type = request.getParameter("type");
		String expStatus = request.getParameter("expStatus");
		Expense expense = new Expense();
		if (type!=null || expStatus!=null) {
			
			expense.setType(type);
			expense.setExpStatus(expStatus);
		}
		try {
			String name = (employeeService.selectEmployeeNameByEmpNo(empNo)).getName();
			
			String roleName = accountService.selectAccountRoleByName(name).getRole();
			Integer a = 0;
			if(roleName == "管理员" || roleName.equals("管理员")){
				a = 1;
			}
			Pager<Expense> pager = expenseService.selectAll(pageNo, empNo, expense.getType(), expense.getExpStatus(),a);
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
	
	
public String removeExpense(HttpServletRequest request,HttpServletResponse response){
		
		String expNo =  request.getParameter("expNo");
		PrintWriter out;
		try {
			expenseService.removeExpense(expNo);
			out = response.getWriter();
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
			
		}
		return "success";
		
	}
	
/**
 * 添加报销单
 * @param request
 * @param response
 * @return
 * @throws Exception 
 */

		public String addExpense(HttpServletRequest request,HttpServletResponse response) throws Exception{

			Integer empNo =  Integer.parseInt(request.getParameter("empNo"));
			String type = request.getParameter("type");
			String expStatus = request.getParameter("expStatus");
			Double money = Double.valueOf( request.getParameter("money"));
			String about = request.getParameter("about");
			Expense expense = new Expense();
			expense.setEmpId(empNo);
			expense.setType(type);
			expense.setExpStatus(expStatus);
			expense.setMoney(money);
			expense.setAbout(about);
			
			try {
				expenseService.addExpense(expense);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("isError", true);
				request.setAttribute("errorMessage", e.getMessage());
				return "fail";
			}
			
			
		}

	
		public String updateExpense(HttpServletRequest request,HttpServletResponse response) throws Exception{

			Integer empNo =  Integer.parseInt(request.getParameter("empNo"));
			String expNo =  request.getParameter("expNo");
			String type = request.getParameter("typetd");
			String expStatus = request.getParameter("expStatustd");
			Double money = Double.valueOf( request.getParameter("money"));
			String about = request.getParameter("about");
			Expense expense = new Expense();
			expense.setEmpId(empNo);
			expense.setExpNo(expNo.substring(2));
			expense.setType(type);
			expense.setExpStatus(expStatus);
			expense.setMoney(money);
			expense.setAbout(about);
			
			try {
				expenseService.updateExpense(expense);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("isError", true);
				request.setAttribute("errorMessage", e.getMessage());
				return "fail";
			}
			
			
		}

		
		
		
	
	
	
	
	
}