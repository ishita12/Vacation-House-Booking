package com.ishita.pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="travellerHostRequest_table")
public class TravellerHostRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "requestID", unique=true, nullable = false)
	private long requestID;
	
	
	
	@OneToOne(mappedBy = "trequest", cascade = CascadeType.ALL)

	private Reply reply;
	
	

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}



	@Column(name = "placeID")
	private long placeID;
	
	
	@Column(name="dateStarted")
	private Date dateStarted;
	
	

	public String getTravellerEmail() {
		return travellerEmail;
	}

	public void setTravellerEmail(String travellerEmail) {
		this.travellerEmail = travellerEmail;
	}



	@Transient
	private String travellerEmail;
	

    @Column(name="travellerName")
	private String travellerName;
	
    
    @Column(name="city")
	private String city;
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTravellerName() {
		return travellerName;
	}

	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}


	@Column(name="dateEnded")
	private Date dateEnded;
	
	





	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Date getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(Date dateEnded) {
		this.dateEnded = dateEnded;
	}



	@Column(name="travelId")
	private long travelId;



	public long getTravelId() {
		return travelId;
	}

	public void setTravelId(long travelId) {
		this.travelId = travelId;
	}


	@Column(name="status")
	private String status;
	
	
	
	

	
  
	
	public long getPlaceID() {
		return placeID;
	}

	public void setPlaceID(long placeID) {
		this.placeID = placeID;
	}





	
	
	public PaymentDetails getPayDeatils() {
		return payDeatils;
	}

	public void setPayDeatils(PaymentDetails payDeatils) {
		this.payDeatils = payDeatils;
	}



	@OneToOne(mappedBy="hostTraveller")
	private PaymentDetails payDeatils;
	

	public long getRequestID() {
		return requestID;
	}

	public void setRequestID(long requestID) {
		this.requestID = requestID;
	}

	



	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
