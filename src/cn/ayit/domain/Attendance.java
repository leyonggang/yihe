package cn.ayit.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String account;
	@Column
	private Date attendTime;
	@Column
	private String type;
	
	@Transient
	private String name;
	
	public Attendance(){
		
	}
	
	public Attendance(int id,String account,Date attendTime,String type,String name){
		this.id = id;
		this.account = account;
		this.attendTime = attendTime;
		this.type = type;
		this.name = name;
	}
	
	
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(Date attendTime) {
		this.attendTime = attendTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
