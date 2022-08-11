package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.constants.Dbconstants;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.jdbc.JDBCNoticeService.IdTitle;

public interface NoticeService {

	int removeNoticeAll(int[] ids);// 공지를 삭제하고 몇개가 삭제되었는지 반환
	int pubNoticeAll(int[] oids,int[] cids);
	int pubNoticeAll(String oidsCSV,String cidsCSV);
	int pubNoticeAll(List<String> oids,List<String> cids);//공지를 공개하고 몇개가 공개되었는지 반환
	boolean insertNotice(Notice notice); //공지를 올리고 성공시 true 실패시 false 반환
	boolean deleteNotice(int id); // 공지를 삭제하고 성공시 true 반환
	boolean updateNotice(Notice notice);//공지를 업데이트하고 성공시 true 반환
	List<Notice> getNoticeNewestList();// 최신 공지를 가져온다	
	List<Notice> getNoticeList();
	List<Notice> getNoticeList(int page);
	List<Notice> getNoticePubList(String field/*title or writer_id*/,String query/*A*/,int page);
	List<Notice> getNoticeList(String field/*title or writer_id*/,String query/*A*/,int page);
	int getNoticeCount();//공지사항 갯수 반환
	int getNoticeCount(String field, String query);
	Notice getNotice(int id);
	IdTitle getPrevNotice(int id);
	IdTitle getNextNotice(int id);
	



}
