package com.newlecture.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/")
public class HomeController  {
	
	
	
	//임의의 메소드에 인자값을 추가하면 스프링에서 제공하는
	//front controller가 이를 제공할 수 있을 경우에 인자를 제공한다.
	@RequestMapping("test")
	public void test(HttpServletResponse response)
	{
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("hello test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("test2")
	@ResponseBody
	public String test2()
	{
		//단순히 return 문자열 형태로 함수를 정의하면
		//해당 문자열 이름을 갖는 페이지 or 타일즈 정보를 찾게된다 
		//ResponseBody 어노테이션 사용시 
		//리턴하는 대상은 뷰 리졸버를 사용하지 않고 그대로 반환됨 
		return "Hello Tester";
	}
	
	
	
	//url 매핑이 클래스가 아니라 메소드에 매핑됨 
	@RequestMapping("index")
	public String index()
	{

		return "root.index";
	}
	
	
	@RequestMapping("help")
	public void help()
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
