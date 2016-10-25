package cn.ayit.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * MetaUsers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yihe_meta_codeTag")
public class CodeTag implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private int tid;
	private Date date;
	private User user;
	private String kg;//开关栓
	private String jc;//稽查


	public CodeTag() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "tid", unique = true, nullable = false)
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account", nullable = true)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "kg")
	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	@Column(name = "jc")
	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}


}