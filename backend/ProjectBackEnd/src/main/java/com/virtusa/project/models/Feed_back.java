package com.virtusa.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Feed_back {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String flat_no;
	private String msg;
	private String at_time;
	
	
	
	public Feed_back() {
		super();
	}



	public Feed_back(int id, String flat_no, String msg, String at_time) {
		super();
		this.id = id;
		this.flat_no = flat_no;
		this.msg = msg;
		this.at_time = at_time;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFlat_no() {
		return flat_no;
	}



	public void setFlat_no(String flat_no) {
		this.flat_no = flat_no;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public String getAt_time() {
		return at_time;
	}



	public void setAt_time(String at_time) {
		this.at_time = at_time;
	}



	@Override
	public String toString() {
		return "Feed_back [id=" + id + ", flat_no=" + flat_no + ", msg=" + msg + ", at_time=" + at_time + "]";
	}



	
	

	
}
