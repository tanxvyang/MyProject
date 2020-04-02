package com.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ssm.pojo.Img;
import com.ssm.service.ImgService;

@Controller
@RequestMapping("/img")
public class ImgController {
	@Autowired
	private ImgService imgService;
	
	@RequestMapping("/addImg")
	public String addImg(Integer id,@RequestParam("imgFile") CommonsMultipartFile imgFile) {
		try {
			imgService.addImg(imgFile.getInputStream(), id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/index.jsp";
	}
	
	@RequestMapping("/findImgById")
	public void findImgById(Integer id,HttpServletResponse response) {
		Img img = imgService.getImgById(id);
		try {
			response.getOutputStream().write(img.getImgFile());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
