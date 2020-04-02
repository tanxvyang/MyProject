package com.ssm.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ssm.dao.CardDao;
import com.ssm.pojo.Card;

@Repository
public class CardDaoImpl implements CardDao{

	private CardDao cardDao;
	
	public void insertCard(Card card) {
		cardDao.insertCard(card);
	}

	public void updateCard(Card card) {
		cardDao.updateCard(card);
	}

	public Card selectCardById(Integer id) {
		return cardDao.selectCardById(id);
	}

	public void setFactory(SqlSessionFactory factory) {
		this.cardDao = factory.openSession().getMapper(CardDao.class);
	}
}
