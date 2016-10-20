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
	private Integer pfid;//父功能
	private Integer type;//类型{1:1级功能;2：2级功能}
	

	// Constructors

	/** default constructor */
	public Function() {
	}

	// Property accessors
	@Id
	@Column(name = "fid", unique = true, nullable = false)
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Column(name = "pfid")
	public Integer getPfid() {
		return pfid;
	}

	public void setPfid(Integer pfid) {
		this.pfid = pfid;
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




	
	
}