package com.virtusa.project.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Usertb {
    
	
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String Flat_no;
	
	private String Name;
	
	private String PhoneNumber;
	
	private String EmailId;
	
	private String Password;
	
	private boolean online;

	
	public Usertb() {
		
		super();
	}

   
    


	





	public Usertb(int id, String flat_no, String name, String phoneNumber, String emailId, String password,
			boolean online) {
		super();
		this.id = id;
		Flat_no = flat_no;
		Name = name;
		PhoneNumber = phoneNumber;
		EmailId = emailId;
		Password = password;
		this.online = online;
	}











	public boolean isOnline() {
		return online;
	}











	public void setOnline(boolean online) {
		this.online = online;
	}











	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFlat_no() {
		return Flat_no;
	}



	public void setFlat_no(String flat_no) {
		Flat_no = flat_no;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getPhoneNumber() {
		return PhoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}



	public String getEmailId() {
		return EmailId;
	}



	public void setEmailId(String emailId) {
		EmailId = emailId;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}











	@Override
	public String toString() {
		return "Usertb [id=" + id + ", Flat_no=" + Flat_no + ", Name=" + Name + ", PhoneNumber=" + PhoneNumber
				+ ", EmailId=" + EmailId + ", Password=" + Password + ", online=" + online + "]";
	}





	

	
		
	
}

