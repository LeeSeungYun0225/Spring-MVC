package com.newlecture.web.constants;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


@Component
public class Dbconstants {

	
	public static DataSource datasource;


	
	public Dbconstants() {
		// TODO Auto-generated constructor stub
	}
	
	
	// static 변수는 setter에 autowired하지 않으면 nullpointer예외 발생 
	@Autowired
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
		
	}

	
	
	
}
