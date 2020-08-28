package com.virtusa.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Managerdefinedtb {
	@Id
	@GeneratedValue
	private int id;
	private String manager_id;
	private String occuation;
	private int fund;
	private String fund_day;
	
	
	public Managerdefinedtb() {
		super();
	}
	
	public Managerdefinedtb(int id, String manager_id, String occuation, int fund ,String fund_day) {
		super();
		this.id = id;
		this.manager_id = manager_id;
		this.occuation = occuation;
		this.fund = fund;
		this.fund_day = fund_day;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getOccuation() {
		return occuation;
	}
	public void setOccuation(String occuation) {
		this.occuation = occuation;
	}
	public int getFund() {
		return fund;
	}
	public void setFund(int fund) {
		this.fund = fund;
	}
    
	public String getFund_day() {
		return fund_day;
	}

	public void setFund_day(String fund_day) {
		this.fund_day = fund_day;
	}

	@Override
	public String toString() {
		return "Managerdefinedtb [id=" + id + ", manager_id=" + manager_id + ", occuation=" + occuation  
				 + ", fund=" + fund + ", fund_day=" + fund_day + "]";
	}

	
	

}
