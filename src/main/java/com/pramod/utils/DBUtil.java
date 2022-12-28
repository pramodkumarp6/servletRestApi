package com.pramod.utils;


import java.sql.SQLException;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
public class DBUtil {
	/*private static final String DB_USERNAME="db.username";
	private static final String DB_PASSWORD="db.password";
	private static final String DB_URL ="db.url";
	private static final String DB_DRIVER_CLASS="driver.class.name";*/
	
	
	private static HikariDataSource dataSource;
	static{
		try {
		
			//InputStream inputStream = DBUtil.class.getResourceAsStream("database.properties");
			//System.out.println(inputStream);
		    dataSource = new HikariDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://192.168.1.6:3306/advance");
			dataSource.setUsername("pramod");
			dataSource.setPassword("pramod");
			dataSource.setMinimumIdle(100);
			dataSource.setMaximumPoolSize(20);//The maximum number of connections, idle or busy, that can be present in the pool.
			dataSource.setAutoCommit(false);
			dataSource.setLoginTimeout(3);
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("DataBase Error: "+e);
		} 
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
}


