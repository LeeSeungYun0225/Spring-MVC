package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newlecture.web.constants.Dbconstants;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;


// 추후에 다른 프로그램을 이용해 구현하게 될 수 있기 때문에
// 인터페이스를 상속받아 구현하고 
// 객체 사용 단에서는 인터페이스로 선언 , 생성하여 결합도를 낮춘다 .

@Service
public class JDBCNoticeService implements NoticeService {
	
	public JDBCNoticeService() {
		// TODO Auto-generated constructor stub
		
		 
	}
	
	
	
	public int removeNoticeAll(int[] ids)// 공지를 삭제하고 몇개가 삭제되었는지 반환
	{
		int deleted = 0;
		
		String params = "";
		
		for(int i=0;i<ids.length;i++)
		{
			params+=ids[i];
			if(i != ids.length-1)
			{
				params+=",";
			}
		}
		
		
		String sql = "DELETE FROM NOTICE WHERE ID IN("+params+")";
		
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
				 
			 
			 Statement statement = con.createStatement();
			 deleted = statement.executeUpdate(sql);
			 // executeUpdate는 insert / delete / update시에 사용하며
			 // 성공한 튜플만큼 개수를 반환한다. 
			 //statement는 PreparedStatement에비해 경량화되어있음
			 
		
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	public int pubNoticeAll(int[] oids,int[] cids)
	{
		List<String> oidsList = new ArrayList<>();
		List<String> cidsList = new ArrayList<>();
		for(int i=0;i<oids.length;i++)
		{
			oidsList.add(String.valueOf(oids[i]));
		}
		for(int i=0;i<cids.length;i++)
		{
			cidsList.add(String.valueOf(cids[i]));
		}
		
		
		
		return pubNoticeAll(oidsList,cidsList);
	}
	
	public int pubNoticeAll(String oidsCSV,String cidsCSV)
	{
		
		int result_O = 0;
		int result_C = 0;
		
		
		
		String sql_O = "UPDATE NOTICE SET public = 1 WHERE ID IN("+oidsCSV+")";
		String sql_C = "UPDATE NOTICE SET public = 0 WHERE ID IN("+cidsCSV+")";
		Connection con = null;
		Statement statement_O = null;
		Statement statement_C = null;
		
		try {
			 con = Dbconstants.datasource.getConnection();
			 
			 con.setAutoCommit(false); // 오토 커밋을 false로 설정 
			 
			 
			 statement_O = con.createStatement();
			 statement_C = con.createStatement();
			 result_O = statement_O.executeUpdate(sql_O);
			 result_C = statement_C.executeUpdate(sql_C);
			 // executeUpdate는 insert / delete / update시에 사용하며
			 // 성공한 튜플만큼 개수를 반환한다. 
			 //statement는 PreparedStatement에비해 경량화되어있음
			 
			 con.commit(); // 모든 쿼리가 실행 완료되었을 시점에 커밋 수행 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(con!= null)// 예외 발생시 커넥션이 열려있으면 
			{
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // 롤백한다 
			}
			e.printStackTrace();
		}
		finally
		{
			
			 try {
				con.close();
				statement_C.close();
				statement_O.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		
		return result_O+result_C;
		
	}
		
	public int pubNoticeAll(List<String> oids,List<String> cids)//공지를 공개하고 몇개가 공개되었는지 반환
	{
		String oidsCSV = String.join(",",oids);
		String cidsCSV = String.join(",",cids);;
		//String.join : 구분자로 문자를 연결해줌 
		
		return pubNoticeAll(oidsCSV,cidsCSV);
	
	}
	
	
	
	public boolean insertNotice(Notice notice) //공지를 올리고 성공시 true 실패시 false 반환
	{
		
		String sql = "INSERT INTO NOTICE(TITLE,CONTENT,WRITER_ID,PUBLIC,REGDATE,FILES) VALUES(?,?,?,?,now(),?)";
		int result = 0;
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, notice.getTitle());
			 statement.setString(2,notice.getContent());
			 statement.setString(3,notice.getWriter_id());
			 statement.setInt(4, notice.getPub()?1:0);
			 statement.setString(5,notice.getFiles());
			  result = statement.executeUpdate();
			 // executeUpdate는 insert / delete / update시에 사용하며
			 // 성공한 튜플만큼 개수를 반환한다. 
			 //statement는 PreparedStatement에비해 경량화되어있음
			 
			 
		
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result!=0?true:false;
	}
	
	public boolean deleteNotice(int id) // 공지를 삭제하고 성공시 true 반환
	{
		
		int deleted = 0;
		

		
		
		String sql = "DELETE FROM NOTICE WHERE ID =?";
		
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1,id);
			 deleted = statement.executeUpdate();
			 // executeUpdate는 insert / delete / update시에 사용하며
			 // 성공한 튜플만큼 개수를 반환한다. 
			 //statement는 PreparedStatement에비해 경량화되어있음
			 
		
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return deleted==1?true:false;
	}
	
	
	
	
	public boolean updateNotice(Notice notice)//공지를 업데이트하고 성공시 true 반환
	{
		return true;
	}
	
	public List<Notice> getNoticeNewestList(){// 최신 공지를 가져온다
		return null;
	}
	
	
	
	
	
	
	public class IdTitle{
		private int id;
		private String title;
		public IdTitle(IdTitle in) {
			// TODO Auto-generated constructor stub
			
			id = in.getId();
			title = in.getTitle();
		}
		
		public IdTitle(int id_,String title_) {
			// TODO Auto-generated constructor stub
			
			id = id_;
			title = title_;
		}
		public String getTitle() {
			return title;
		}
		
		public int getId() {
			return id;
		}
		
		public void setTitle_(String title_) {
			this.title = title_;
		}
		
		public void setId_(int id_) {
			this.id = id_;
		}
	}
	
	public List<Notice> getNoticeList(){
		return getNoticeList("title","",1);
		
	}
	
	public List<Notice> getNoticeList(int page){
		return  getNoticeList("title","",page);
		
	}
	
public List<Notice> getNoticePubList(String field/*title or writer_id*/,String query/*A*/,int page){
		
		List<Notice> list = new ArrayList<Notice>();
	
		String sql = " SELECT * FROM("
				+ "	SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM , note.*"
				+ " FROM  (SELECT * FROM NOTICE_VIEW WHERE "+field+" LIKE ? AND public = 1) as note"
				+ "	 ) as b"
				+ "	WHERE NUM BETWEEN ? AND ?";
				
	
		
		try {
			
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, "%"+query+"%");
			 statement.setInt(2,1+(page-1)*10);
			 statement.setInt(3,page*10);
			 int cmtcount;
			 String files;
			 int hit;
			 int id;
			 String title;
			 String writer_id;
			 Date regdate;
			 int pub;

			 ResultSet result = statement.executeQuery();
			 
			 while(result.next()) {
				 id = result.getInt("ID");
				 title = result.getString("TITLE");
				 writer_id= result.getString("WRITER_ID");
				 regdate = result.getDate("REGDATE");
				 hit = result.getInt("HIT");
				 files = result.getString("FILES");
				 cmtcount = result.getInt("COMMENT_COUNT");
				 pub = result.getInt("public");
				 System.out.println(id);
				 NoticeView notice = new NoticeView(id,title,writer_id,regdate,hit,files,cmtcount);
				 notice.setPubl(pub);
				 list.add(notice);
			 }
			 
			 
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		

		
		
		return list;
		
	}
	
	public List<Notice> getNoticeList(String field/*title or writer_id*/,String query/*A*/,int page){
		
		List<Notice> list = new ArrayList<Notice>();
	
		String sql = " SELECT * FROM("
				+ "								SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM , note.*"
				+ "							 FROM  (SELECT * FROM NOTICE_VIEW WHERE "+field+" LIKE ?) as note"
				+ "							  ) as b"
				+ "							 	WHERE NUM BETWEEN ? AND ?";
				
	
		
		try {
			 System.out.println("jdbc Enter");
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, "%"+query+"%");
			 statement.setInt(2,1+(page-1)*10);
			 statement.setInt(3,page*10);
			 int cmtcount;
			 String files;
			 int hit;
			 int id;
			 String title;
			 String writer_id;
			 Date regdate;
			 int pub;

			 ResultSet result = statement.executeQuery();
			 
			 while(result.next()) {
				 id = result.getInt("ID");
				 title = result.getString("TITLE");
				 writer_id= result.getString("WRITER_ID");
				 regdate = result.getDate("REGDATE");
				 hit = result.getInt("HIT");
				 files = result.getString("FILES");
				 cmtcount = result.getInt("COMMENT_COUNT");
				 pub = result.getInt("public");
				 NoticeView notice = new NoticeView(id,title,writer_id,regdate,hit,files,cmtcount);
				 notice.setPubl(pub);
				 list.add(notice);
			 }
			 System.out.println(list.toString()+ "list");
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		

		
		
		return list;
		
	}
	
	public int getNoticeCount()
	{	
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field, String query)
	{
		int count = 0;
		
		String sql ="SELECT COUNT(ID) COUNT FROM("
				+ "				SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM ,nn.*"
				+ "				 FROM (SELECT * FROM NOTICE n WHERE n."+ field + " LIKE ?) nn ) b";
				
				
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, "%"+query+"%");
			 
			 ResultSet result = statement.executeQuery();
			 
			 if(result.next())
			 {
				 count = result.getInt("count");
			 }
			
			 System.out.println(count);
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	
	public Notice getNotice(int id)
	{
		
		Notice notice = new Notice();
		
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, id);
			 
			 
			 ResultSet result = statement.executeQuery();
			 
			 while(result.next()) {
				 int id_ = result.getInt("ID");
				 String title = result.getString("TITLE");
				 String writer_id= result.getString("WRITER_ID");
				 Date regdate = result.getDate("REGDATE");
				 int hit = result.getInt("HIT");
				 String files = result.getString("FILES");
				 String content = result.getString("CONTENT");
				 notice = new Notice(id_,title,writer_id,regdate,hit,files,content);
			 }
			 
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return notice;
		
	}
	
	public IdTitle getPrevNotice(int id)
	{
		int id_=0;
		String title_ = null;
		IdTitle notice = new IdTitle(id_,title_);
		String sql = "SELECT id,title FROM NOTICE WHERE id IN"
				+ "(SELECT id FROM NOTICE WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE id = ?))"
				+ "ORDER BY REGDATE DESC LIMIT 1";
	
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, id);
			 
			 
			 ResultSet result = statement.executeQuery();
			 
			 if(result.next()) {
				 id_ = result.getInt("ID");
				 title_ = result.getString("TITLE");
				 notice = new IdTitle(id_,title_);
			 }

				
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return notice;
		
	}
	
	public IdTitle getNextNotice(int id)
	{
		
		int id_=0;
		String title_ = null;
		IdTitle notice = new IdTitle(id_,title_);
		String sql = "SELECT id,title FROM NOTICE WHERE id IN"
				+ "(SELECT id FROM NOTICE WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE id = ?))"
				+ "ORDER BY REGDATE ASC LIMIT 1";
		
		try {
			 Connection con = Dbconstants.datasource.getConnection();
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, id);
			 
			 
			 ResultSet result = statement.executeQuery();
			 
			 if(result.next()) {
				 id_ = result.getInt("ID");
				 title_ = result.getString("TITLE");
				 notice = new IdTitle(id_,title_);
			 }

				
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return notice;
		
		
		
	}




	
	
	

}
