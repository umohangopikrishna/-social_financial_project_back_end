package com.virtusa.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserInfotb {
	@Id
	@GeneratedValue
	private int id;
	private String datatopay;
	private String paydata;
	private String occuation;
	private int paymoney;
	private int paymode;
	private String flat_id;
  
	
    
	public UserInfotb() {
		super();
	}
     
	
	public UserInfotb(int id, String datatopay, String paydata, String occuation, int paymode, String flat_id ,int paymoney) {
		super();
		this.id = id;
		this.datatopay = datatopay;
		this.paydata = paydata;
		this.occuation = occuation;
		this.paymode = paymode;
		this.flat_id = flat_id;
		this.paymoney = paymoney;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatatopay() {
		return datatopay;
	}

	public void setDatatopay(String datatopay) {
		this.datatopay = datatopay;
	}

	public String getPaydata() {
		return paydata;
	}

	public void setPaydata(String paydata) {
		this.paydata = paydata;
	}

	public String getOccuation() {
		return occuation;
	}

	public void setOccuation(String occuation) {
		this.occuation = occuation;
	}

	public int getPaymode() {
		return paymode;
	}

	public void setPaymode(int paymode) {
		this.paymode = paymode;
	}

	public String getFlat_id() {
		return flat_id;
	}

	public void setFlat_id(String flat_id) {
		this.flat_id = flat_id;
	}

	public int getPaymoney() {
		return paymoney;
	}


	public void setPaymoney(int paymoney) {
		this.paymoney = paymoney;
	}


	@Override
	public String toString() {
		return "UserInfotb [id=" + id + ", datatopay=" + datatopay + ", paydata=" + paydata + ", occuation=" + occuation
				+ ", paymoney=" + paymoney + ", paymode=" + paymode + ", flat_id=" + flat_id + "]";
	}


	

 
	
}
