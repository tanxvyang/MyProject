package com.ssm.service;

import java.io.InputStream;

import com.ssm.pojo.Card;

public interface CardService {
	public void addOrModifyCard(Card card,InputStream uploadFile,String imgPath,String suffix) throws Exception;
	
	public void modifyCard(Card card);
	
	public Card findCardById(Integer id);
}
