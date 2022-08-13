package com.newlecture.web.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;


@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("list")
	public String list()
	{
		List<Notice> list = noticeService.getNoticeList("TITLE", "", 1);
		return "notice.list";
	}
	
	@RequestMapping("detail")
	public String detail()
	{
		List<Notice> list = noticeService.getNoticeList("TITLE", "", 1);
		
		return "notice.detail";
	}
}
