package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.DeptDao;
import com.web.mapper.DeptMapper;
import com.web.mapper.RowMapper;
import com.web.pojo.Dept;
import com.web.util.JDBCTemplate;

public class DeptDaoImpl implements DeptDao {
	JDBCTemplate<Dept> temp = new JDBCTemplate<Dept>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	public void deleteDept(String deptNo) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_dept ")
		.append(" where ")
		.append(" 	 t_dept_no  = ? ")
		.toString();
		temp.delete(sql, deptNo);
		
	}

	public void insertDept(Dept dept) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time) ")
		.append(" values  ")
		.append("  	(?,?,?,?,now()) ")
		.toString();
	temp.insert(sql, dept.getDeptNo(),dept.getDeptName(),dept.getDeptLoc(),dept.getDeptManager());
		
	}

	public List<Dept> selectAllDept() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.toString();
		return temp.selectAll(new DeptMapper(), sql);	
	}

	public void updateDept(Dept dept) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_dept ")
		.append(" set ")
		.append(" 	t_dept_name = ?,t_dept_loc = ? ,t_dept_manager = ?")
		.append(" where ")
		.append(" 	t_dept_no = ? ")
		.toString();
		temp.update(sql,dept.getDeptName(),dept.getDeptLoc(),dept.getDeptManager(),dept.getDeptNo() );
		
	}

	public List<Dept> selectDeptByPage(Integer pageNo, Integer pageSize) {
		String sb = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.append(" limit ")
		.append(" 	?,? ")
		.toString();
		List<Object> param = new ArrayList<Object>();
		param.add((pageNo - 1) * pageSize);
		param.add(pageSize);

		return temp.selectAll(new DeptMapper(), sb.toString(), param.toArray());
	}

	public Integer countDept() {
		String sb = new StringBuffer()
		.append(" select ")
		.append(" 	count(t_dept_no) nums ")
		.append(" from ")
		.append(" 	t_dept ")
		.toString();
		return tempCount.selectOne(new RowMapper<Integer>(){

			public Integer mapperObject(ResultSet rs) throws Exception {
				return rs.getInt("nums");
			}
			
		}, sb.toString());
	}

	public List<Dept> selectDeptByDepyNoName(Dept dept) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.append(" where  ")
		.append(" t_dept_no = ?  ")
		.append(" or  t_dept_name = ? ")
		.toString();
		return temp.selectAll(new DeptMapper(), sql,dept.getDeptNo(),dept.getDeptName());	
	}

	public Dept selectDeptBydeptNo(String deptNo) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.append(" where  ")
		.append("  t_dept_no = ? ")
		.toString();
		return temp.selectOne(new DeptMapper(), sql,deptNo);
	}

	public Dept selectDeptByName(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.append(" where  ")
		.append("  t_dept_manager = ? ")
		.toString();
		return temp.selectOne(new DeptMapper(), sql,name);
	}

	public Dept selectDeptBydeptName(String deptName) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.append(" where  ")
		.append(" 	t_dept_name = ? ")
		.toString();
		return temp.selectOne(new DeptMapper(), sql,deptName);	
	}

	public Dept selectDeptBydeptNoAndName(Dept dept) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	t_dept_no,t_dept_name,t_dept_loc,t_dept_manager,t_create_time ")
		.append(" from ")
		.append(" 	t_dept ")
		.append(" where  ")
		.append(" t_dept_no = ?  ")
		.append(" and  t_dept_name = ? ")
		.toString();
		return temp.selectOne(new DeptMapper(), sql,dept.getDeptNo(),dept.getDeptName());	
	}


}
