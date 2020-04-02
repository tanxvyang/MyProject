package com.web.service.impl;

import java.util.List;

import com.web.dao.EmployeeDao;
import com.web.dao.HolidayDao;
import com.web.pojo.Employee;
import com.web.pojo.Holiday;
import com.web.pojo.Role;
import com.web.service.HolidayService;
import com.web.util.Constants;
import com.web.util.Pager;

public class HolidayServiceImpl implements HolidayService{
	private HolidayDao holidayDao;
	private EmployeeDao employeeDao;
	
	public Pager<Holiday> selectAll(Integer pageNo, String type, String name,String selectName,
			String status,Integer a) throws Exception {
		Pager<Holiday> pager = new Pager<Holiday>();
		pager.setPageNo(pageNo);
		Integer totalCount = holidayDao.countUser(type, name,selectName, status,a);
		if(totalCount == null) {
			totalCount = 0;
		}
		pager.setTotalPage(totalCount, Constants.PAGE_SIZE_4);
		List<Holiday> users = holidayDao.selectUserByPage(type, name, selectName,status,pageNo,Constants.PAGE_SIZE_4,a);
		pager.setList(users);
		return pager;
		
	}

	public void removeHoliday(Integer id) throws Exception {
		try {
			System.out.println("id = "+id);
			String status = holidayDao.selectStatusById(id).getStatus();
			if(status.equals("已提交") || status == "已提交"){
				throw new Exception("此请假单已经提交，无法删除");
			}else{
				holidayDao.removeHoliday(id);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void addHoliday(Holiday holiday) throws Exception {
		try {
			Employee employees = employeeDao.selectEmployeeByname(holiday.getUsername());
			if (employees !=null) {
				holidayDao.insertHoliday(holiday);
			}else{
				throw new Exception("添加失败！当前用户状态异常");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Holiday getHolidayMessage(Integer id) throws Exception {
		Holiday holiday = holidayDao.selectStatusById(id);
		if (holiday == null || holiday.equals("")) {
			throw new Exception(" 该请假单不存在或已被删除！ ");
		}else{
		Employee employees = employeeDao.selectEmpByName(holiday.getUsername());
		if ( employees!= null) {
			return holiday;
		}else{
			throw new Exception("该用户不存在");
		}
		}
	}
	
	public void updateHoliday(Holiday holiday) throws Exception {
		try {

			if (holiday.getType() != null && holiday.getStatus()!= null) {
				// 找到报销单
				Holiday holidays = holidayDao.selectStatusById(holiday.getId());
				System.out.println("请假单"+holidays);
				if (holidays != null) {
					// 找到该用户,确认用户状态
					
					/**
					 * 更改了调用的dao层方法,如果出错,修改下面注释
					 */
					Employee employees = employeeDao.selectEmployeeByname(holidays.getUsername());
					if (employees != null) {
						holidayDao.updateHoliday(holiday);
					} else {
						throw new Exception("修改失败！当前用户状态异常");
					}
				} else {
					throw new Exception("修改失败！未找到请假条！");
				}
			} else {
				throw new Exception("修改失败！状态和类型均不能为空！");
			}
		} catch (Exception e) {
		
			throw e;
		}
	}
	
	
	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}
	public HolidayDao getHolidayDao() {
		return holidayDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}




}
