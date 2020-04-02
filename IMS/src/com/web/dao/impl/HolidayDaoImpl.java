package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.HolidayDao;
import com.web.mapper.HolidayMapper;
import com.web.mapper.RowMapper;
import com.web.pojo.Holiday;
import com.web.util.JDBCTemplate;

public class HolidayDaoImpl implements HolidayDao{
	JDBCTemplate<Holiday> temp = new JDBCTemplate<Holiday>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	
	public Integer countUser(String type, String name, String selectName,String status,Integer a) {
		StringBuffer sb = new StringBuffer()
			.append(" select ")
			.append(" 	count(id) nums ")
			.append(" from ")
			.append(" 	t_holiday ")
			.append(" where ")
			.append(" 	1 = 1 ");
			List<Object> param = new ArrayList<Object>();
			if(a == 0 && a.equals(0)){
				sb.append(" and t_holiday_user = ? ");
				param.add(name);
			}
			if(type != null && !type.equals("")) {
				sb.append(" and t_holiday_type = ? ");
				param.add(type);
			}
			if(selectName != null && !selectName.equals("")) {
				sb.append(" and t_holiday_user like ? ");
				param.add("%"+selectName+"%");
			}
			if(status != null && !status.equals("")) {
				sb.append(" and t_holiday_status = ? ");
				param.add(status);
			}
			return tempCount.selectOne(new RowMapper<Integer>() {
				public Integer mapperObject(ResultSet rs) throws Exception {
					return rs.getInt("nums");
				}
			}, sb.toString(), param.toArray());
	}
	public List<Holiday> selectUserByPage(String type, String name,String selectName,
			String status, Integer pageNo, Integer pageSize,Integer a) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time ")
		.append(" from ")
		.append(" 	t_holiday ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		//a是0时，为普通用户,为1时，为管理员
		if(a == 0 && a.equals(0)){
			sb.append(" and t_holiday_user = ? ");
			param.add(name);
		}
		
		if(type != null && !type.equals("")) {
			sb.append(" and t_holiday_type = ? ");
			param.add(type);
		}
		if(selectName != null && !selectName.equals("")) {
			sb.append(" and t_holiday_user like ? ");
			param.add("%"+selectName+"%");
		}
		if(status != null && !status.equals("")) {
			sb.append(" and t_holiday_status = ? ");
			param.add(status);
		}
		if(a == 1 && a.equals(1)){
			sb.append(" and (t_holiday_status = '提交' or t_holiday_user = ?)");
			param.add(name);
		}
		sb.append(" limit ")
		  .append(" 	?,? ");
		param.add((pageNo - 1) * pageSize);
		param.add(pageSize);
	
	return temp.selectAll(new HolidayMapper(), sb.toString(), param.toArray() );
	}
	
	public void removeHoliday(Integer id) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_holiday ")
		.append(" where ")
		.append(" 	 id  = ? ")
		.toString();
		temp.delete(sql, id);
	}
	public Holiday selectStatusById(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time ")
		.append(" from ")
		.append(" 	t_holiday ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		System.err.println(sql);
		return temp.selectOne(new HolidayMapper(),sql,id);	
	}
	public void removeHolidayByName(String name) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_holiday ")
		.append(" where ")
		.append(" 	 t_holiday_user  = ? ")
		.toString();
		temp.delete(sql, name);
	}
	public void insertHoliday(Holiday holiday) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_holiday(t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time) ")
		.append(" values  ")
		.append("  	(?,?,?,?,?,?,now()) ")
		.toString();
	temp.insert(sql,holiday.getUsername(),holiday.getType(),holiday.getBz(),holiday.getStartTime(),holiday.getEndTime(),holiday.getStatus());
	}
	public List<Holiday> selectAllHoliday() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time ")
		.append(" from ")
		.append(" 	t_holiday ")
		.toString();
		return temp.selectAll(new HolidayMapper(), sql );
	}
	public void updateHoliday(Holiday holiday) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_holiday ")
		.append(" set ")
		.append(" 	t_holiday_type = ? ,t_holiday_bz = ?,t_start_time = ?,t_end_time= ?,t_holiday_status= ?,t_create_time = now()")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		temp.update(sql,holiday.getType(),holiday.getBz(),holiday.getStartTime(),holiday.getEndTime(),holiday.getStatus(),holiday.getId());
	}
	

}
