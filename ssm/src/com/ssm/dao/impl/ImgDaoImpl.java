package com.ssm.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.dao.ImgDao;
import com.ssm.pojo.Img;
@Repository
public class ImgDaoImpl implements ImgDao{
	
	private ImgDao imgDao;
	
	public void insertImg(Img img) {
		imgDao.insertImg(img);
	}

	public Img selectImgById(Integer id) {
		return imgDao.selectImgById(id);
	}
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.imgDao = factory.openSession().getMapper(ImgDao.class);
	}
	
}
