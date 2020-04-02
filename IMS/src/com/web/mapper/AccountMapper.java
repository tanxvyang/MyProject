package com.web.mapper;

import java.sql.ResultSet;

import com.web.pojo.Account;
import com.web.pojo.User;

public class AccountMapper implements RowMapper<Account>{

	public Account mapperObject(ResultSet rs) throws Exception {
		Account account = new Account();
		account.setAccountNum(rs.getString("t_account_number"));
		account.setId(rs.getInt("id"));
		account.setName(rs.getString("t_name"));
		account.setRole(rs.getString("t_role"));
		account.setStatus(rs.getString("t_account_status"));
		return account;
	}

}
