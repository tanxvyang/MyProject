package com.ssm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Card;
import com.ssm.service.CardService;
import com.ssm.util.FileUtil;

@Controller
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	@RequestMapping("/addOrModifyCard")
	public ModelAndView addOrModifyCard(Card card,@RequestParam("imgFile") CommonsMultipartFile imgFile,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String imgPath = request.getSession().getServletContext().getRealPath("/img");
		String fileName = imgFile.getFileItem().getName();
		String suffix = "";
		if(!fileName.equals("")) {
			suffix = fileName.substring(fileName.indexOf('.'), fileName.length());
		}
		try {
			cardService.addOrModifyCard(card, imgFile.getInputStream(), imgPath, suffix);
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("isError", true);
			mv.addObject("errMsg", e.getMessage());
		}
		Integer id = card.getId();
		mv.addObject("id", id);
		mv.setViewName("redirect:findCardById.do");
		System.out.println("跳转展示页面");
		return mv;
	}
	
	@RequestMapping("/toModifyCard")
	public ModelAndView toModifyCard(Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("card", cardService.findCardById(id));
		mv.setViewName("/addOrModifyCard.jsp");
		return mv;
	}
	
	@RequestMapping("/findCardById")
	public ModelAndView findCardById(Integer id) {
		Card card = cardService.findCardById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("card", card);
		mv.setViewName("/Card.jsp");
		System.out.println("展示名片");
		return mv;
	}
}
