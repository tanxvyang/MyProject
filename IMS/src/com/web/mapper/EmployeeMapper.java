package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.Employee;


public class EmployeeMapper implements RowMapper<Employee>{

	public Employee mapperObject(ResultSet rs) throws Exception {
		Employee worker = new Employee();
		worker.setId(rs.getInt("id"));
		worker.setEmpNo(rs.getInt("t_emp_no"));
		worker.setName(rs.getString("t_emp_name"));
		worker.setDept(rs.getString("t_emp_dept"));
		worker.setSex(rs.getString("t_sex"));
		worker.setEducation(rs.getString("t_education"));
		worker.setEmail(rs.getString("t_email"));
		worker.setPhone(rs.getString("t_phone"));
		worker.setEntryTime(rs.getString("t_entry_time"));
		worker.setCreateTime(rs.getString("t_create_time"));
		return worker;
	}

}
