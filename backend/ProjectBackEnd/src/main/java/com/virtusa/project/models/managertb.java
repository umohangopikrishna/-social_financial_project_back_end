package com.virtusa.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class managertb {
	@Id
	@GeneratedValue
	private int id;
	private String manager_id;
	private String manager_name;
	private String manager_password;
	private String manager_gmail;
	private String complex_name;
	private String complex_id;
	private String phone_number;
	private boolean online;
	
	public managertb() {
		super();
	}
	
	public managertb(int id, String manager_id, String manager_name, String manager_password, String manager_gmail,
			String complex_name, String complex_id, String phone_number, boolean online) {
		super();
		this.id = id;
		this.manager_id = manager_id;
		this.manager_name = manager_name;
		this.manager_password = manager_password;
		this.manager_gmail = manager_gmail;
		this.complex_name = complex_name;
		this.complex_id = complex_id;
		this.phone_number = phone_number;
		this.online = online;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	public String getManager_gmail() {
		return manager_gmail;
	}
	public void setManager_gmail(String manager_gmail) {
		this.manager_gmail = manager_gmail;
	}
	public String getComplex_name() {
		return complex_name;
	}
	public void setComplex_name(String complex_name) {
		this.complex_name = complex_name;
	}
	public String getComplex_id() {
		return complex_id;
	}
	public void setComplex_id(String complex_id) {
		this.complex_id = complex_id;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@Override
	public String toString() {
		return "managertb [id=" + id + ", manager_id=" + manager_id + ", manager_name=" + manager_name
				+ ", manager_password=" + manager_password + ", manager_gmail=" + manager_gmail + ", complex_name="
				+ complex_name + ", complex_id=" + complex_id + ", phone_number=" + phone_number + ", online=" + online
				+ "]";
	}
	
	
	
	
	
	
	

}
