package com.library.model;

public class StaffsModel {
	
	private String Staff_ID;
    private String Name;
    private String Password;
    private String Contact;
    
	public StaffsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StaffsModel(String staff_ID, String name, String password, String contact) {
		super();
		Staff_ID = staff_ID;
		Name = name;
		Password = password;
		Contact = contact;
	}

	public String getStaff_ID() {
		return Staff_ID;
	}

	public void setStaff_ID(String staff_ID) {
		Staff_ID = staff_ID;
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
		return "Staffs [Staff_ID=" + Staff_ID + ", Name=" + Name + ", Password=" + Password + ", Contact=" + Contact
				+ "]";
	}

}
