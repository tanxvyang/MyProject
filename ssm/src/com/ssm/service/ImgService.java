package com.ssm.service;

import java.io.File;
import java.io.InputStream;

import com.ssm.pojo.Img;

public interface ImgService {
	
	public void addImg(InputStream is,Integer id);
	
	public Img getImgById(Integer id);
}
