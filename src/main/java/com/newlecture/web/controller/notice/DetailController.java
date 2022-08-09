package com.newlecture.web.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DetailController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		

		ModelAndView mv = new ModelAndView("notice.detail");
		
		Detail dt = new Detail("제목입니다","2022-08-08","Lee",10,"","테스트 게시글");
		
		mv.addObject("data",dt);	
		return mv;
		
		
	}
	
	
	public class Detail
	{
		private String name;
		private String date;
		private String writer;
		private int hit;
		private String file;
		private String text;
		
		
		public Detail(String name_,String date_,String writer_,int hit_,String file_,String text_)
		{
			name = name_;
			date = date_;
			writer = writer_;
			hit = hit_;
			file = file_;
			text = text_;
			
		}
		
		public Detail()
		{
			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}

		public int getHit() {
			return hit;
		}

		public void setHit(int hit) {
			this.hit = hit;
		}

		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
		
	}
}
