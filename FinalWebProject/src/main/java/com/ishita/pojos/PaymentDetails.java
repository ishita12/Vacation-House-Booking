package com.ishita.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="paymentDetails_table")
public class PaymentDetails {

	@Id
	@GeneratedValue(generator="generator")
	@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="hostTraveller"))
		
	@Column(name = "paymentID", unique=true, nullable = false)
	private long paymentID;
	
	
	@Column(name="travellerID")
	private long travellerID;
	
	
	@Column(name="cardNumber")
	private String cardNumber;
	
	
	@Column(name="cvv")
	private String cvv;
	
	
	@Column(name="expiryDate")
	private Date expiryDate;
	

	@Column(name="paymentDate")
	private Date paymentDate;



	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	@OneToOne
	@PrimaryKeyJoinColumn
	
	private TravellerHostRequest hostTraveller;

	

	public TravellerHostRequest getHostTraveller() {
		return hostTraveller;
	}


	public void setHostTraveller(TravellerHostRequest hostTraveller) {
		this.hostTraveller = hostTraveller;
	}


	public long getPaymentID() {
		return paymentID;
	}


	public void setPaymentID(long paymentID) {
		this.paymentID = paymentID;
	}


	public long getTravellerID() {
		return travellerID;
	}


	public void setTravellerID(long travellerID) {
		this.travellerID = travellerID;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCvv() {
		return cvv;
	}


	public void setCvv(String cvv) {
		this.cvv = cvv;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}





	
	
}
