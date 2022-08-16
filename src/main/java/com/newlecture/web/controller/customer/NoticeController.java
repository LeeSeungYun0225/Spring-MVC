package com.newlecture.web.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;


@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("list") // RequestParam("")은 쿼리스트링의 대상을 다르게 명명하여 사용할 수 있게 함 	
	//다만 이 경우 쿼리스트링 p가 없을 경우 문제 발생 
	//기본값을 설정해주면 된다.(스프링에서 지원하는 기능) 
	public String list(@RequestParam(required = false,name="p") int page)//String p)//HttpServletRequest request)
	{
		//String p = request.getParameter("p");
		System.out.println(page);
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
