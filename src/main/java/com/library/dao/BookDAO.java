package com.library.dao;

public class BookDAO {
	
	private String jdbcURL ="jdbc:mysql://localhost:30006/userdb";
	private String jdbcUserName ="root";
	private String jdbcPassword ="Akash#19122017**";
	
	private static final String INSERT_USER_SQL="INSERT INTO books"+"(id,title,author,isbn,genre,available_copies)VALUES"+"(?,?,?,?,?,?);";
	private static final String SELECT_USER_BY_ID="SELECT*FROM USERS WHERE ID=?;";
	private static final String SELECT_All_USER="SELECT*FROM books;";
	private static final String DELETE_USER_SQL="DELETE*FROM USERS WHERE ID=?;";
	private static final String UPDATE_USERS_SQL="UPDATE USERS SET id=?,title=?,author=?,isbn=?,genre=?,available_copies=?;";
	
	public BookDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Connection getConnection() {
		Connection connection=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return connection;
	}

}

