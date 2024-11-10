package com.library.dao;

public class TransactionDAO {
		
		private String jdbcURL ="jdbc:mysql://localhost:30006/userdb";
		private String jdbcUserName ="root";
		private String jdbcPassword ="Akash#19122017**";
		
		private static final String INSERT_USER_SQL="INSERT INTO books"+"(id,book_id,member_id,borrow_id,return_date)VALUES"+"(?,?,?,?,?);";
	

}
