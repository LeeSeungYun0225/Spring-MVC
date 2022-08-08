package com.newlecture.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class IndexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("data","HelloSpringMvc~"); //뷰 페이지에 전달할 모델 추가 
		 
		mv.setViewName("index"); //뷰 페이지 요청 
		//WEB-INF/view/index.jsp >> 상대주소 :: 이 url과 같은 경로에 있다고 생각하고 탐색 
		
		
		
		return mv;
	}

}
