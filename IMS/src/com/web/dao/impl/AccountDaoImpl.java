package com.web.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.AccountDao;
import com.web.mapper.AccountMapper;
import com.web.mapper.RowMapper;
import com.web.pojo.Account;
import com.web.util.JDBCTemplate;

public class AccountDaoImpl implements AccountDao{
	JDBCTemplate<Account> temp = new JDBCTemplate<Account>();
	JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
	public Integer countUser(String empNo, String status, String role) {
		StringBuffer sb = new StringBuffer()
			.append(" select ")
			.append(" 	count(id) nums ")
			.append(" from ")
			.append(" 	t_account ")
			.append(" where ")
			.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		if(status != null && !status.equals("")) {
			sb.append(" and t_account_status = ? ");
			param.add(status);
		}
		if(empNo != null && !empNo.equals("")) {
			sb.append(" and t_account_number like ? ");
			param.add("%"+empNo+"%");
		}
		if(role != null && !role.equals("")) {
			sb.append(" and t_role = ? ");
			param.add(role);
		}
		return tempCount.selectOne(new RowMapper<Integer>() {
			public Integer mapperObject(ResultSet rs) throws Exception {
				return rs.getInt("nums");
			}
		}, sb.toString(), param.toArray());
	}

	public List<Account> selectUserByPage(String empNo, String status,
			String role, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_account_number,t_name,t_account_status,t_role ")
		.append(" from ")
		.append(" 	t_account ")
		.append(" where ")
		.append(" 	1 = 1 ");
		List<Object> param = new ArrayList<Object>();
		if(empNo != null && !empNo.equals("")) {
			sb.append(" and t_account_number like ? ");
			param.add("%"+empNo+"%");
		}
		if(status != null && !status.equals("")) {
			sb.append(" and t_account_status = ? ");
			param.add(status);
		}
		if(role != null && !role.equals("")) {
			sb.append(" and t_role = ? ");
			param.add(role);
		}
		sb.append(" limit ")
		  .append(" 	?,? ");
		param.add((pageNo - 1) * pageSize);
		param.add(pageSize);
	
	return temp.selectAll(new AccountMapper(), sb.toString(), param.toArray() );
	}

	public void removeAccount(Integer id) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_account ")
		.append(" where ")
		.append(" 	 id  = ? ")
		.toString();
		temp.delete(sql, id);
	}

	public void addAccount(String num, String name, String sel1, String sel2) {
		String sql = new StringBuffer()
			.append(" insert into ")
			.append(" 	t_account(id,t_account_number,t_name,t_account_status,t_role) ")
			.append(" values ")
			.append(" 	(?,?,?,?,?) ")
			.toString();
		temp.insert(sql, null,num,name,sel1,sel2);
	}

	public Account selectAccountByName(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_account_number,t_name,t_account_status,t_role ")
		.append(" from ")
		.append(" 	t_account ")
		.append(" where ")
		.append("   t_name = ?  ")
		.toString();
		return temp.selectOne(new AccountMapper(),sql,name);
	}

	public List<Account> selectOneAccount(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_account_number,t_name,t_account_status,t_role ")
		.append(" from ")
		.append(" 	t_account ")
		.append(" where ")
		.append("   id = ?  ")
		.toString();
		return temp.selectAll(new AccountMapper(),sql,id);
	}

	public void updateAccount(Integer onlyId, String status, String role) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_account ")
		.append(" set ")
		.append(" 	t_account_status = ?,t_role = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
	temp.update(sql, status,role,onlyId);
	}

	public Account selectAccountNameByEmpNo(Integer id) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_account_number,t_name,t_account_status,t_role ")
		.append(" from ")
		.append(" 	t_account ")
		.append(" where ")
		.append("   id = ?  ")
		.toString();
		return temp.selectOne(new AccountMapper(),sql,id);
	}

	public Account selectAccountByRoleName(String name) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_account_number,t_name,t_account_status,t_role ")
		.append(" from ")
		.append(" 	t_account ")
		.append(" where ")
		.append("   t_role = ?  ")
		.toString();
		return temp.selectOne(new AccountMapper(),sql,name);
	}

	public List<Account> selectRole() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,t_account_number,t_name,t_account_status,t_role ")
		.append(" from ")
		.append(" 	t_account ")
		.append(" group by ")
		.append(" 	t_role ")
		.toString();
		return temp.selectAll(new AccountMapper(), sql);	
	}


	public void updateAccountNewByOle(String NewName, String OleName) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_account ")
		.append(" set ")
		.append(" 	t_role = ? ")
		.append(" where ")
		.append(" 	t_role = ? ")
		.toString();
	temp.update(sql, NewName,OleName);
	}

	public void removeAccountByName(String name) {
		String sql = new StringBuffer()
		.append(" delete from ")
		.append(" 	t_account ")
		.append(" where ")
		.append(" 	 t_name  = ? ")
		.toString();
		temp.delete(sql, name);
	}


}
