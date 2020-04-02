package com.web.dao;

import java.util.List;

import com.web.pojo.Employee;
import com.web.pojo.Holiday;

public interface HolidayDao {
	public Integer countUser(String type, String name,String selectName, String status,Integer a);
	
	public List<Holiday> selectUserByPage(String type, String name,String selectName, String status, Integer pageNo, Integer pageSize,Integer a);
	
	public void removeHoliday(Integer id);
	
	public Holiday selectStatusById(Integer id);
	
	public void removeHolidayByName(String name);
	//插入
	public void insertHoliday(Holiday holiday);
	//更改
	public void updateHoliday(Holiday holiday);
	//查所有
	public List<Holiday> selectAllHoliday();
}
