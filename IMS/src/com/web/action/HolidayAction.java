package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import com.web.pojo.Expense;
import com.web.pojo.Holiday;
import com.web.service.AccountService;
import com.web.service.HolidayService;
import com.web.service.MergeService;
import com.web.util.Pager;

public class HolidayAction {
	private HolidayService holidayService;
	private AccountService accountService;
	private MergeService mergeService;

	public String selectAllHoliday(HttpServletRequest request, HttpServletResponse resp){
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String type =request.getParameter("type");
		String username = request.getParameter("username");
		String selectName = request.getParameter("name");
		String status = request.getParameter("status");
		try {
			String roleName = accountService.selectAccountRoleByName(username).getRole();
			Integer a = 0;
			if(roleName == "管理员" || roleName.equals("管理员")){
				a = 1;
			}
			Pager<Holiday> pager = holidayService.selectAll(pageNo,type,username,selectName,status,a);
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
	
	public String removeHoliday(HttpServletRequest request, HttpServletResponse resp){
		Integer deptNo = Integer.parseInt(request.getParameter("deptNo"));
		System.out.println("deptNo = "+deptNo);
		PrintWriter out;
		try {
			holidayService.removeHoliday(deptNo);
			out = resp.getWriter();
			out.write("true");
			out.flush();
			out.close();
		} catch (Exception e) {
			
		}
		return "success";
	}
	
	
	

	/**
	 * @author tan
	 * 请假详情
	 * 
	 * 12-14
	 * @param request
	 * @param resp
	 * @return
	 */
	public String getHolidayMessage(HttpServletRequest request,HttpServletResponse response){
		Integer id = Integer.parseInt(request.getParameter("QJId"));
		System.out.println("id = "+id);
		try {
			Holiday holiday = holidayService.getHolidayMessage(id);
			System.out.println("holidayaction-getMessage-holiday="+holiday);
			PrintWriter out;
			out = response.getWriter();
			String json = JSONObject.fromObject(holiday).toString();
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
	 * 添加请假
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */


	public String addHoliday(HttpServletRequest request,HttpServletResponse response) throws Exception{

//			Integer empNo =  Integer.parseInt(request.getParameter("empNo"));
//	System.out.println("expAction empNo = "+empNo);
		String bz = request.getParameter("bz");
		String username = request.getParameter("username");
					String startTime = request.getParameter("startTime");
					String endTime = request.getParameter("endTime");
					
						String type = request.getParameter("type");
					String status = request.getParameter("status");
					Holiday holiday = new Holiday();
					holiday.setUsername(username);
					holiday.setBz(bz);
					holiday.setStartTime(startTime);
					holiday.setEndTime(endTime);
					holiday.setStatus(status);
					holiday.setType(type);
//			
					System.out.println(holiday);
			try {
				holidayService.addHoliday(holiday);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("isError", true);
				request.setAttribute("errorMessage", e.getMessage());
				return "fail";
			}
//			
//			
		}


	
	
		public String updateHoliday(HttpServletRequest request,HttpServletResponse response) throws Exception{

			Integer id = Integer.parseInt(request.getParameter("QJId"));
//System.out.println("HolidayAction id = "+id);
			String bz = request.getParameter("bz");
//System.out.println("HolidayAction bz = "+bz);
			String startTime = request.getParameter("startTime");
//System.out.println("HolidayAction startTime = "+startTime);
			String endTime = request.getParameter("endTime");
//System.out.println("HolidayAction endTime = "+endTime);
			
				String type = request.getParameter("type");
//System.out.println("HolidayAction type = "+type);
			String status = request.getParameter("status");
//System.out.println("HolidayAction status = "+status);
			Holiday holiday = new Holiday();
			holiday.setId(id);
			holiday.setBz(bz);
			holiday.setStartTime(startTime);
			holiday.setEndTime(endTime);
			holiday.setStatus(status);
			holiday.setType(type);
				try {
					System.out.println("holidat = "+ holiday);
					holidayService.updateHoliday(holiday);
					return "success";
				} catch (Exception e) {
				e.printStackTrace();
					request.setAttribute("isError", true);
					request.setAttribute("errorMessage", e.getMessage());
					return "fail";
				}
				
				
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

		public void setHolidayService(HolidayService holidayService) {
			this.holidayService = holidayService;
		}
	
		public HolidayService getHolidayService() {
			return holidayService;
		}
}