package com.hjh.demo.springJdbcTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Tester {

	public static void main(String args[]) throws IOException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("create table if not exists  temp(id bigint primary key,name varchar(32))");

		jdbcTemplate.execute(new StatementCallback<Object>() {

			public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
				stmt.executeUpdate("insert into temp(id,name) values(" + System.currentTimeMillis() + ",'2')");
				return null;
			}

		});

	}

}
