package com.newlecture.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

// 객체를 return하면 알아서 JSON으로 변경하여 넘겨준다. 
@RestController("apiNoticeController")//id 명시 :: 다른 NoticeController와 아이디가 충돌나는것을 방지 
@RequestMapping("/api/notice/")
public class NoticeController {
	
	
	@Autowired
	private NoticeService service;
	
	
	@RequestMapping("list")
	public List<Notice> list()
	{
		List<Notice> list = service.getNoticeList("TITLE", "", 1);
		
		
		return list;
	} 
}
