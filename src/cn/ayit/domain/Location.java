package cn.ayit.domain;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.struts2.json.annotations.JSON;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String account;
	//@Column
	//private Date locationDate;
	//@Column
	//private Time LocationTime;
	@Column
	private Date locationTime;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@Column
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/*@JSON(format="yyyy-MM-dd")
	public Date getLocationDate() {
		return locationDate;
	}

	public void setLocationDate(Date locationDate) {
		this.locationDate = locationDate;
	}

	@JSON(format="HH:mm:ss")
	public Time getLocationTime() {
		return LocationTime;
	}

	public void setLocationTime(Time locationTime) {
		LocationTime = locationTime;
	}*/
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getLocationTime() {
		return locationTime;
	}

	public void setLocationTime(Date locationTime) {
		this.locationTime = locationTime;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
