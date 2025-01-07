package com.library.model;

public class AdminModel {
	
	private String User_ID;
    private String Name;
    private String Password;
    private String Contact;
    
	public AdminModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminModel(String user_ID, String name, String password, String contact) {
		super();
		User_ID = user_ID;
		Name = name;
		Password = password;
		Contact = contact;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	@Override
	public String toString() {
		return "AdminModel [User_ID=" + User_ID + ", Name=" + Name + ", Password=" + Password + ", Contact=" + Contact
				+ "]";
	}

}
