package com.nts.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * DB 설정만 따로 담고 있는 클래스
 * 
 * @author 고영빈
 * 
 */

@Configuration
@EnableTransactionManagement
public class DbConfig {

	private final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user7";
	private final String DB_USER = "user7";
	private final String DB_PASSWORD = "user7";

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USER);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}
	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
