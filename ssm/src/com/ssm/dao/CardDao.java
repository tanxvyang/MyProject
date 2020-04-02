package com.ssm.dao;

import com.ssm.pojo.Card;

public interface CardDao {
	public void insertCard(Card card);
	
	public void updateCard(Card card);
	
	public Card selectCardById(Integer id);
}
