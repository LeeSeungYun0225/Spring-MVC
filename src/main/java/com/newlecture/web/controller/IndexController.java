package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class IndexController  {
	
	
	@RequestMapping("/index")
	public void abc()
	{
		System.out.println("test Message");
	}
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//	
//		ModelAndView mv = new ModelAndView("root.index");
//		mv.addObject("data","HelloSpringMvc~"); //뷰 페이지에 전달할 모델 추가 
//		 
//	
//		//WEB-INF/view/index.jsp >> 상대주소 :: 이 url과 같은 경로에 있다고 생각하고 탐색 
//		
//		
//		
//		return mv;
//	}

}
