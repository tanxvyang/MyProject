package com.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.pojo.User;
import com.web.service.AccountService;
import com.web.service.UserService;

public class UserAction {
	private UserService userService;
	private AccountService accountService;
	
	
	public String login(HttpServletRequest request, HttpServletResponse resp) {
		String empNo = request.getParameter("empNo");
		String password = request.getParameter("password");
		String verificationCode  = request.getParameter("code");
		User user = null;
		try {
			
			String empNa = userService.selectUserByEmpNo(Integer.valueOf(empNo)).getEmpNa();
			String status = accountService.selectAccountRoleByName(empNa).getStatus();
			if(status == "正常" || status.equals("正常")){
				HttpSession session = request.getSession();
				if (!verificationCode.equals(session.getAttribute("verificationCode"))) {
					request.setAttribute("isError", true);
					request.setAttribute("errorMessage", "验证码错误!请重新输入!");
					return "fail";
				}else{
					user = userService.login(empNo, password);
					session.setAttribute("user", user);
					return "success";
				}
				
			}else{
				request.setAttribute("isError", true);
				request.setAttribute("errorMessage", "账户状态异常!");
				return "fail";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
	}
	
	public String updatePwd(HttpServletRequest request, HttpServletResponse resp){
		Integer empid = Integer.valueOf( request.getParameter("empid"));
		String oldPwd =request.getParameter("oldPwd");
		String newPwd =request.getParameter("newPwd");
		try {
			userService.updateUserPwd(empid, oldPwd, newPwd);
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("errorMessage", e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AccountService getAccountService() {
		return accountService;
	}
}
