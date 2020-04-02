package com.ssm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.ImgDao;
import com.ssm.pojo.Img;
import com.ssm.service.ImgService;

@Service
public class ImgServiceImpl implements ImgService{
	
	@Autowired
	private ImgDao imgDao;
	
	@Transactional
	public void addImg(InputStream is, Integer id) {
		Img img = new Img();
		img.setId(id);
		byte[] bs = null;
		try {
			bs = new byte[is.available()];
			is.read(bs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		img.setImgFile(bs);
		imgDao.insertImg(img);
	}

	public Img getImgById(Integer id) {
		return imgDao.selectImgById(id);
	}
}
