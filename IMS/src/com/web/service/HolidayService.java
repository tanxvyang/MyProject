package com.web.service;

import com.web.pojo.Employee;
import com.web.pojo.Holiday;
import com.web.util.Pager;

public interface HolidayService {
	public Pager<Holiday> selectAll(Integer pageNo, String type, String name,String selectName, String status,Integer a) throws Exception ;
	
	public void removeHoliday(Integer id) throws Exception;
	//12-14  请假信息
	public Holiday getHolidayMessage(Integer id) throws Exception;
	//添加	
	public void  addHoliday(Holiday holiday)throws Exception;
	//修改
	public void updateHoliday(Holiday holiday) throws Exception;
}
