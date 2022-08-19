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
	//choicebox같은 동일한 name을 갖는 태그가 여러개일 경우 배열로 받아올 수 있음 
	public String reg(String title,String content,String category,String check,String[] choicebox,String radiobox)
	{
		for(int i=0;i<choicebox.length;i++)
		{
			System.out.println("check BOX : " + choicebox[i]);
		}
		
		return "title :" + title + " content : "+ content + " category : " + category + " radio : " + radiobox;
	}
	
	@RequestMapping("edit")
	public String edit()
	{
		return "";
	}	
	
}
