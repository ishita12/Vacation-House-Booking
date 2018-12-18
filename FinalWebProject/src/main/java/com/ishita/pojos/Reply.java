package com.ishita.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
@Table(name="reply_table")
public class Reply {

@Id
	@GeneratedValue(generator="generator")
	@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="trequest"))
		@Column(name="replyID",unique=true,nullable=false)

	
	private long replyID;
	
	
	
	
	@OneToOne
	@PrimaryKeyJoinColumn
	

	private TravellerHostRequest trequest;
	
	
	public TravellerHostRequest getTrequest() {
		return trequest;
	}
	public void setTrequest(TravellerHostRequest trequest) {
		this.trequest = trequest;
	}
	public Reply(){
		
	}
	public long getReplyID() {
		return replyID;
	}



	public void setReplyID(long replyID) {
		this.replyID = replyID;
	}



	public String getHostName() {
		return hostName;
	}



	public void setHostName(String hostName) {
		this.hostName = hostName;
	}



	public String getTravellerName() {
		return travellerName;
	}



	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}


    @Column(name="hostName")
	private String hostName;
	
	
    @Column(name="travellerName")
	private String travellerName;
	
	
    @Column(name="status")
	private String status;
	
	
    @Column(name="message")
	private String message;
	
	
}
