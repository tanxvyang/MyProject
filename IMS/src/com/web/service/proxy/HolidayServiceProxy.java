package com.web.service.proxy;

import com.web.pojo.Employee;
import com.web.pojo.Holiday;
import com.web.service.HolidayService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class HolidayServiceProxy implements HolidayService{
	
	private Transaction trans;
	private HolidayService holidayService;
	
	public Pager<Holiday> selectAll(Integer pageNo, String type, String name,String selectName,
			String status,Integer a) throws Exception {
		trans.begin();
		Pager<Holiday> Pager;
		try {
			Pager = holidayService.selectAll(pageNo,type,name,selectName,status,a);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return Pager;
	}
	
	public void removeHoliday(Integer id) throws Exception {
		trans.begin();
		try {
			holidayService.removeHoliday(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
	}
	
	
	
	public void addHoliday(Holiday holiday) throws Exception {
		trans.begin();
		try {
			holidayService.addHoliday(holiday);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}
	
	public Holiday getHolidayMessage(Integer id) throws Exception {
		trans.begin();
		Holiday holiday ;
		try {
			holiday = holidayService.getHolidayMessage(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return holiday;
	}
	
	public void updateHoliday(Holiday holiday) throws Exception {
		trans.begin();
		try {
			holidayService.updateHoliday(holiday);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}
	
	
	
	
	
	
	
	
	public Transaction getTrans() {
		return trans;
	}


	public void setTrans(Transaction trans) {
		this.trans = trans;
	}


	public HolidayService getHolidayService() {
		return holidayService;
	}


	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}






}
