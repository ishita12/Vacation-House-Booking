package com.ishita.pojos;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.commons.CommonsMultipartFile;



@Entity
@Table(name="user_table")

public class User {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userID", unique=true, nullable = false)
	private long userID;
		
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
    private String lastName;
	
	
	@Column(name="filename")
	private String filename;
	
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name="phoneNumber")
	
	private String phonenumber;
	
	
	@Transient
	private int numberOfPlaces;
	
	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	@Column(name="email")
	private String email;
	
	
	
	@Transient
	private CommonsMultipartFile photo;
	  
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="confirmPassword")
	private String confirmPassword;
	
	
	@Column(name="role")
	private String role;
	

	public User() {
		// TODO Auto-generated constructor stub
	}

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Places> places = new HashSet<Places>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<TravellerHostRequest> travellerHostRequest = new HashSet<TravellerHostRequest>();
	
	
	

	@OneToMany(cascade=CascadeType.ALL)
	private Set<TravellerEntry> travellerEntry=new HashSet<TravellerEntry>();
	
	
	
	
	
	

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	

	public Set<TravellerHostRequest> getTravellerHostRequest() {
		return travellerHostRequest;
	}

	public void setTravellerHostRequest(Set<TravellerHostRequest> travellerHostRequest) {
		this.travellerHostRequest = travellerHostRequest;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public Set<Places> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Places> places) {
		this.places = places;
	}

	

	public Set<TravellerEntry> getTravellerEntry() {
		return travellerEntry;
	}

	public void setTravellerEntry(Set<TravellerEntry> travellerEntry) {
		this.travellerEntry = travellerEntry;
	}
	
}
