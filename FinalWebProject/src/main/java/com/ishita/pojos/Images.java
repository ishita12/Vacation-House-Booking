package com.ishita.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="image_table")
public class Images {

	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "imageID", unique=true, nullable = false)
	private long imageID;
	
	
	
	@Column(name="fileName")
	private String fileName;     
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeID")
	private Places places;
	
	
	public long getImageID() {
		return imageID;
	}

	public void setImageID(long imageID) {
		this.imageID = imageID;
	}

	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Places getPlaces() {
		return places;
	}

	public void setPlaces(Places places) {
		this.places = places;
	}


	
	
	
}
