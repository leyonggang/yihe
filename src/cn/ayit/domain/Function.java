package cn.ayit.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * ZagpSysCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yihe_meta_function")
public class Function implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fid;
	private String name;//名称
	private Integer type;//类型
	
	private Set<Role> roles;//角色
	

	// Constructors

	/** default constructor */
	public Function() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "fid", unique = true, nullable = false)
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade=CascadeType.REFRESH)
	@JoinTable(name="yihe_map_role_function",inverseJoinColumns=@JoinColumn(name="rid"),
			joinColumns=@JoinColumn(name="fid"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	
}