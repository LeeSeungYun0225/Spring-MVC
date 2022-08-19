package com.newlecture.web.controller.admin.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("adminNoticeController")//동일한 이름의 클래스를 구분하기 위함 
@RequestMapping("/admin/board/notice/")
public class NoticeController {

	
	@RequestMapping("list")
	public String list()
	{
		return "";
	}
	
	
	@RequestMapping("reg")
	@ResponseBody
	public String reg(String title,String content)
	{
		return "title :" + title + "content : "+ content;
	}
	
	@RequestMapping("edit")
	public String edit()
	{
		return "";
	}	
	
}
