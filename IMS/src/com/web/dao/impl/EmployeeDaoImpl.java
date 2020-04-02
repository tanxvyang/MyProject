package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.EmployeeDao;
import com.web.mapper.DeptMapper;
import com.web.mapper.EmployeeMapper;
import com.web.mapper.RowMapper;
import com.web.mapper.UserMapper;
import com.web.pojo.Employee;
import com.web.service.EmployeeService;
import com.web.util.JDBCTemplate;

public class EmployeeDaoImpl implements EmployeeDao{
	JDBCTemplate<Employee> temp = new JDBCTemplate<Employee>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	public List<Employee> selectAllWorker() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.toString();
		return temp.selectAll(new EmployeeMapper(), sql);
	}

	public Integer countUser(String empNo, String name, String dapt) {
		StringBuffer sb = new StringBuffer()
				.append(" select ")
				.append(" 	count(id) nums ")
				.append(" from ")
				.append(" 	t_employee ")
				.append(" where ")
				.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		if(empNo != null && !empNo.equals("")) {
			sb.append(" and t_emp_no = ? ");
			param.add(empNo);
		}
		if(name != null && !name.equals("")) {
			sb.append(" and t_emp_name like ? ");
			param.add("%"+name+"%");
		}
		if(dapt != null && !dapt.equals("")) {
			sb.append(" and t_emp_dept = ? ");
			param.add(dapt);
		}
		return tempCount.selectOne(new RowMapper<Integer>() {
			public Integer mapperObject(ResultSet rs) throws Exception {
				return rs.getInt("nums");
			}
		}, sb.toString(), param.toArray());
	}

	public List<Employee> selectUserByPage(String empNo, String name,
			String dapt, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer()
			.append(" select ")
			.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
			.append(" from ")
			.append(" 	t_employee ")
			.append(" where ")
			.append(" 	1 = 1 ");
			List<Object> param = new ArrayList<Object>();
			if(empNo != null && !empNo.equals("")) {
				sb.append(" and t_emp_no = ? ");
				param.add(empNo);
			}
			if(name != null && !name.equals("")) {
				sb.append(" and t_emp_name like ? ");
				param.add("%"+name+"%");
			}
			if(dapt != null && !dapt.equals("")) {
				sb.append(" and t_emp_dept = ? ");
				param.add(dapt);
			}
			sb.append(" limit ")
			  .append(" 	?,? ");
			param.add((pageNo - 1) * pageSize);
			param.add(pageSize);
		
		return temp.selectAll(new EmployeeMapper(), sb.toString(), param.toArray() );
	}

	public void addEmployee(Integer No, String name, String sel1,
			String sel2, String time) {
		String sql = new StringBuffer()
			.append(" insert into ")
			.append(" 	t_employee(t_emp_no,t_emp_name,t_sex,t_emp_dept,t_entry_time) ")
			.append(" values ")
			.append(" 	(?,?,?,?,?) ")
			.toString();
		temp.insert(sql, No,name,sel1,sel2,time);
	}

	public Employee selectEmpByName(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append("   t_emp_name = ?  ")
		.toString();
		return temp.selectOne(new EmployeeMapper(),sql,name);
	}

	public List<Employee> selectUserBydept(String deptName) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_dept = ? ")
		.toString();
		
	return temp.selectAll(new EmployeeMapper(),sql, deptName );
	}

	public Integer countUser(String deptName) {
		
			String sb = new StringBuffer()
			.append(" select ")
			.append(" 	count(t_emp_dept) nums ")
			.append(" from ")
			.append(" 	t_employee ")
			.append(" where ")
			.append(" t_emp_dept = ? ")
			.toString();
			return tempCount.selectOne(new RowMapper<Integer>(){

				public Integer mapperObject(ResultSet rs) throws Exception {
					return rs.getInt("nums");
				}
				
			}, sb.toString(),deptName );
		
	}
	
	public List<Employee> selectOneEmployee(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		return temp.selectAll(new EmployeeMapper(), sql,id);
	}

	public void updateEmployee(Integer id, String sex,
			String dept, String entryTime) {
			String sql = new StringBuffer()
			.append(" update ")
			.append(" 	t_employee ")
			.append(" set ")
			.append(" 	t_sex = ?,t_emp_dept = ?,t_entry_time = ? ")
			.append(" where ")
			.append(" 	id = ? ")
			.toString();
		temp.update(sql, sex,dept,entryTime,id);
	}

	public void removeEmployee(Integer id) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	 id  = ? ")
		.toString();
		temp.delete(sql, id);
	}

	public Employee getEmployeeMessage(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		return temp.selectOne(new EmployeeMapper(), sql,id);
	}

	public Employee selectEmployeeByEmpNo(Integer empNo) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_no = ? ")
		.toString();
		return temp.selectOne(new EmployeeMapper(), sql,empNo);
	}

	public Employee selectEmployeeByname(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_name = ? ")
		.toString();
		return temp.selectOne(new EmployeeMapper(), sql,name);
	}

	public Employee selectEmployeeByEmpNoAndName(Integer empNo, String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_no = ? ")
		.append(" and ")
		.append(" 	t_emp_name = ? ")
		.toString();
		return temp.selectOne(new EmployeeMapper(), sql,empNo,name);
	}

	public List<Employee>  selectOneNo(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time ")
		.append(" from ")
		.append(" 	t_employee ")
		.append(" where ")
		.append(" 	t_emp_name = ? ")
		.toString();
		return temp.selectAll(new EmployeeMapper(), sql,name);
	}

	public void updateEmployeeDeptNameByDeptName(String oldName, String newName) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_employee ")
		.append(" set ")
		.append(" 	t_emp_dept = ? ")
		.append(" where ")
		.append(" 	t_emp_dept = ? ")
		.toString();
	temp.update(sql,newName,oldName);
	}


}
