package com.web.mapper;

import java.sql.ResultSet;
import java.util.Date;

import com.web.pojo.Expense;

public class ExpenseMapper  implements RowMapper<Expense>{

	public Expense mapperObject(ResultSet rs) throws Exception {
		Expense expense = new Expense();
		expense.setExpNo(String.valueOf(rs.getInt("t_exp_no")));
		expense.setEmpId(rs.getInt("t_emp_id"));
		expense.setType(rs.getString("t_exp_type"));
		expense.setMoney(rs.getDouble("t_exp_money"));
		expense.setExpStatus(rs.getString("t_exp_status"));
		expense.setCreateTime(new Date( rs.getDate("t_exp_time").getTime()));
		expense.setAbout(rs.getString("t_exp_about"));
		return expense;
	}
	
	

}
