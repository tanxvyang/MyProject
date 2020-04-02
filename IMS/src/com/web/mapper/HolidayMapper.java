package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Holiday;

public class HolidayMapper implements RowMapper<Holiday>{

	public Holiday mapperObject(ResultSet rs) throws Exception {
		Holiday holiday = new Holiday();
		holiday.setId(rs.getInt("id"));
		holiday.setUsername(rs.getString("t_holiday_user"));
		holiday.setType(rs.getString("t_holiday_type"));
		holiday.setBz(rs.getString("t_holiday_bz"));
		holiday.setStartTime(rs.getString("t_start_time"));
		holiday.setEndTime(rs.getString("t_end_time"));
		holiday.setStatus(rs.getString("t_holiday_status"));
		holiday.setCreateTime(new Date(rs.getDate("t_create_time").getTime()));
		return holiday;
	}

}
