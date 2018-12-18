package com.ishita.pojos;

import java.sql.Date;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="places_table")
public class Places {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "placeID", unique=true, nullable = false)
	private long placeID;
	
	
	@Column(name="hostId")
	private long hostId;
	
	public long getHostId() {
		return hostId;
	}


	public void setHostId(long hostId) {
		this.hostId = hostId;
	}



	@Column(name = "file_name")
	private String file_name;    
	
	public String getFile_name() {
		return file_name;
	}


	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}


	public CommonsMultipartFile[] getPhoto() {
		return photo;
	}


	public void setPhoto(CommonsMultipartFile[] photo) {
		this.photo = photo;
	}


//	public User getUserPlace() {
//		return userPlace;
//	}
//
//
//	public void setUserPlace(User userPlace) {
//		this.userPlace = userPlace;
//	}


	public Set<TravellerHostRequest> getTravellerHostReq() {
		return travellerHostReq;
	}


	public void setTravellerHostReq(Set<TravellerHostRequest> travellerHostReq) {
		this.travellerHostReq = travellerHostReq;
	}


	@Transient
	private CommonsMultipartFile[] photo; 
	
	@Column(name="placeName")
	private String placeName;
	
	
	@Column(name="startDate")
	private Date startDate;
	
	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	@Column(name="endDate")
	private Date endDate;
	
	


	@Column(name="rent")
	private String rent;
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "userID", nullable = false)
//	private User userPlace;
	
	

	@OneToMany(mappedBy="places",cascade=CascadeType.ALL)
	private Set<Images> images = new HashSet<Images>();
	

	@OneToMany(fetch = FetchType.LAZY)
	private Set<TravellerHostRequest> travellerHostReq = new HashSet<TravellerHostRequest>();
	


	

	public long getPlaceID() {
		return placeID;
	}


	public void setPlaceID(long placeID) {
		this.placeID = placeID;
	}


	public String getPlaceName() {
		return placeName;
	}


	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}


	/*public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

*/
	public String getRent() {
		return rent;
	}


	public void setRent(String rent) {
		this.rent = rent;
	}


	
	
	
	
	public Set<Images> getImages() {
		return images;
	}


	public void setImages(Set<Images> images) {
		this.images = images;
	}

	

	
	
	
	
	
	
}
