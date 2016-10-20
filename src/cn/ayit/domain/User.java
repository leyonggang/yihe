package cn.ayit.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "yihe_meta_user")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account;
	private String name;
	private String phone;
	private Dept dept;
	private String password;
	private int type;//类型

	private Set<Function> functions;
	// Constructors

	/** default constructor */
	public User() {
	}

	// Property accessors
	@Id
	@Column(name = "account", unique = true, nullable = false, length = 20)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "phone", length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "did", nullable = true)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	

	@ManyToMany(cascade=CascadeType.REFRESH)
	@JoinTable(name="yihe_map_user_function",inverseJoinColumns=@JoinColumn(name="fid"),
			joinColumns=@JoinColumn(name="account"))
	public Set<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}


}