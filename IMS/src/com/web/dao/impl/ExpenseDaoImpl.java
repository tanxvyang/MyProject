package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.ExpenseDao;
import com.web.mapper.ExpenseMapper;
import com.web.mapper.RowMapper;
import com.web.pojo.Expense;
import com.web.util.JDBCTemplate;

public class ExpenseDaoImpl implements ExpenseDao {
	JDBCTemplate<Expense> temp = new JDBCTemplate<Expense>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	public Integer countExpense(String type, String expStatus, Integer empId,Integer a) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	count(t_exp_no) nums ")
		.append(" from ")
		.append(" 	t_expense ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		if(a == 0 && a.equals(0)){
			sb.append(" and t_emp_id = ? ");
			param.add(empId);
		}
		
		if(type != null && !type.equals("")  && !type.equals("请选择")) {
			sb.append(" and t_exp_type = ? ");
			param.add(type);
		}
		if(expStatus != null && !expStatus.equals("")  && !expStatus.equals("请选择")) {
			sb.append(" and t_exp_status = ? ");
			param.add(expStatus);
		}
			return tempCount.selectOne(new RowMapper<Integer>() {
				public Integer mapperObject(ResultSet rs) throws Exception {
					return rs.getInt("nums");
				}
			}, sb.toString(), param.toArray());
	}

	public void deleteExpense(Integer expNo) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_expense ")
		.append(" where ")
		.append(" 	 t_exp_no  = ? ")
		.toString();
		temp.delete(sql, expNo);
	}

	public void insertExpense(Expense expense) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_expense(t_emp_id,t_exp_type,t_exp_money,t_exp_status,t_exp_time,t_exp_about) ")
		.append(" values  ")
		.append("  	(?,?,?,?,now(),?) ")
		.toString();
	temp.insert(sql,expense.getEmpId(),expense.getType(),expense.getMoney(),expense.getExpStatus(),expense.getAbout());
		
		
	}

	public List<Expense> selectAllExpense() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_exp_no,t_emp_id,t_exp_type,t_exp_money,t_exp_status,t_exp_time,t_exp_about ")
		.append(" from ")
		.append(" 	t_expense ")
		.toString();
		return temp.selectAll(new ExpenseMapper(), sql);	
	}

	public List<Expense> selectExpenseByPage(Integer empId , String type, String expStatus,
			Integer pageNo, Integer pageSize,Integer a) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	t_exp_no,t_emp_id,t_exp_type,t_exp_money,t_exp_status,t_exp_time,t_exp_about ")
		.append(" from ")
		.append(" 	t_expense  ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		//a是0时，为普通用户,为1时，为管理员
		if(a == 0 && a.equals(0)){
			sb.append(" and t_emp_id = ? ");
			param.add(empId);
		}
		if(type != null && !type.equals("")) {
			sb.append(" and t_exp_type = ? ");
			param.add(type);
		}
		if(expStatus != null && !expStatus.equals("") ) {
			sb.append(" and t_exp_status = ? ");
			param.add(expStatus);
		}
		if(a == 1 && a.equals(1)){
			sb.append(" and (t_exp_status = 1 or t_emp_id = ?)");
			param.add(empId);
		}
		sb.append(" limit ")
		  .append(" 	?,? ");
		param.add((pageNo - 1) * pageSize);
		param.add(pageSize);
	
	return temp.selectAll(new ExpenseMapper(), sb.toString(), param.toArray() );

	}

	public Expense selectExpenseByexpNo(Integer expNo) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_exp_no,t_emp_id,t_exp_type,t_exp_money,t_exp_status,t_exp_time,t_exp_about ")
		.append(" from ")
		.append(" 	t_expense ")
		.append("  where  ")
		.append("  t_exp_no = ?  ")
		.toString();
		return temp.selectOne(new ExpenseMapper(), sql,expNo);	
	}

	public void updateExpense(Expense expense) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_expense ")
		.append(" set ")
		.append(" 	t_exp_type = ?,t_exp_money = ? ,t_exp_status = ?,t_exp_about = ?")
		.append(" where ")
		.append(" 	t_exp_no = ? ")
		.toString();
		temp.update(sql,expense.getType(),expense.getMoney(),expense.getExpStatus(),expense.getAbout(),Integer.valueOf((expense.getExpNo()).substring(2)));
		
	}

	public void removeExpenseByName(Integer empNo) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_expense ")
		.append(" where ")
		.append(" 	 t_emp_id  = ? ")
		.toString();
		temp.delete(sql, empNo);
	}

}
