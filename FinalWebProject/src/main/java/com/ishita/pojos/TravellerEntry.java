package com.ishita.pojos;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="customerEntry_table")
public class TravellerEntry {

	
	public TravellerEntry(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "travellerEntryID", unique=true, nullable = false)
	private long travellerEntryID;
	
	@Column(name="city")
	private String city;
	
	
	public long getTravellerEntryID() {
		return travellerEntryID;
	}

	public void setTravellerEntryID(long travellerEntryID) {
		this.travellerEntryID = travellerEntryID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	@Column(name="sDate")
	
	private Date sDate;
	
	
	
	@Column(name="eDate")

	
	private Date eDate;
	
//	@ManyToOne(cascade=CascadeType.ALL) 
//	@JoinColumn(name="userID", nullable=false)
//	private User userEntry;
	
	
	@Column(name="travellerId")
	private long travellerId;
//
//
	public long getTravellerId() {
	return travellerId;
	}
//
	public void setTravellerId(long travellerId) {
		this.travellerId = travellerId;
	}

//	public User getUserEntry() {
//		return userEntry;
//	}
////
//	public void setUserEntry(User userEntry) {
//	this.userEntry = userEntry;
//	}
	
}
