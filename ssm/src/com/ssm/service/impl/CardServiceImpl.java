package com.ssm.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.CardDao;
import com.ssm.pojo.Card;
import com.ssm.service.CardService;
import com.ssm.util.FileUtil;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardDao cardDao;
	
	@Transactional
	public void addOrModifyCard(Card card,InputStream uploadFile,String imgPath,String suffix) throws Exception {
		if(card.getId() == null) {
			//新增
//			String fileName = imgFile.getFileItem().getName();
			//用户默认头像
			card.setImg("1.jpg");
			cardDao.insertCard(card);
			try {
				//文件上传
				FileUtil.uploadFile(uploadFile, imgPath+"/"+card.getId()+suffix);
				//如果文件上传成功,将用户头像置为用户上传的图片
				card.setImg(card.getId()+suffix);
				cardDao.updateCard(card);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}else {
			//修改
			if(!suffix.equals("")) {
				try {
					FileUtil.uploadFile(uploadFile, imgPath+"/"+card.getImg());
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
			cardDao.updateCard(card);
		}
	}
	
	@Transactional
	public void modifyCard(Card card) {
		cardDao.updateCard(card);
		
	}

	public Card findCardById(Integer id) {
		return cardDao.selectCardById(id);
	}
	

}
