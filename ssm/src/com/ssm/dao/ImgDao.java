package com.ssm.dao;

import com.ssm.pojo.Img;

public interface ImgDao {
	public void insertImg(Img img);
	
	public Img selectImgById(Integer id);
}
