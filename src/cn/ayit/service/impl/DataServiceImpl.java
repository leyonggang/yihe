package cn.ayit.service.impl;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import cn.ayit.dao.BaseDao;
import cn.ayit.domain.CodeTag;
import cn.ayit.domain.Dept;
import cn.ayit.domain.Function;
import cn.ayit.domain.User;
import cn.ayit.domain.data.ComboBox;
import cn.ayit.service.DataService;
import cn.ayit.utils.CommonUtil;
import cn.ayit.utils.MD5Util;

@Service("dataService")
public class DataServiceImpl implements DataService{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DataServiceImpl.class);
	
	@Resource
	BaseDao baseDao;
	@Resource(name="simpleJdbcTemplate")
	SimpleJdbcTemplate simple;
	
	@Resource(name="simpleJdbcTemplateRb")
	SimpleJdbcTemplate simpleRb;
	
	
	@Transactional
	@Override
	public void setPassword(String username, String password) {
		User user=baseDao.getById(User.class,username);
		user.setPassword(MD5Util.md5(password));
		baseDao.saveOrUpdate(user);
		
	}
	
	@Override
	public List<Map<String, Object>> findWeixiu(String string) {
		String hql="select new map(" +
				"u.account as account," +
				"d.did as did," +
				"d.name as dname," +
				"u.name as name," +
				"u.phone as phone) " +
			"from User u left join u.dept d "
			+ "inner join u.functions f where f.fid=?";
		return baseDao.query(hql,22);
	}

	@Override
	public List<Map<String, Object>> findXunxian(String string) {
		String hql="select new map(" +
				"u.account as account," +
				"d.did as did," +
				"d.name as dname," +
				"u.name as name," +
				"u.phone as phone) " +
			"from User u left join u.dept d "
			+ " inner join u.functions f where f.fid=?";
		return baseDao.query(hql,23);
	}
	
	@Override
	public List<Map<String, Object>> f11Datagrid(Map<String, Object> map) {
		String year=map.get("year")==null?"%":map.get("year").toString();
		String compqtype=map.get("compqtype")==null?"%":"%"+map.get("compqtype").toString()+"%";
		String communityname=map.get("communityname")==null?"%":"%"+map.get("communityname").toString()+"%";
		String ownername=map.get("ownername")==null?"%":"%"+map.get("ownername").toString()+"%";
		String buildNo=map.get("buildNo")==null?"%":"%"+map.get("buildNo").toString()+"%";
		String unitNo=map.get("unitNo")==null?"%":"%"+map.get("unitNo").toString()+"%";
		String floorNo=map.get("floorNo")==null?"%":"%"+map.get("floorNo").toString()+"%";
		
		String sql="select  substr(housecode,6,3) buildNo,substr(housecode,10,2) unitNo,substr(housecode,13,2) floorNo, "
				+ "housecode, compqtype, communitycode, communityname, sy_bh, buildingname, ownername, address, contactphone,  "
				+ "combuildarea, sf_status, jc_status, dt_value, boltstatus, chargemonth  "
				+ "from view_qiyeapp "
				+ "where substr(chargemonth,1,4)=? "
				+ "and compqtype like ? and communityname like ?  "
				+ "and ownername like ? and substr(housecode,6,3) like ?  "
				+ "and substr(housecode,10,2) like ? and substr(housecode,13,2) like ?";
		return simple.queryForList(sql, year,compqtype,communityname,ownername,buildNo,unitNo,floorNo);
	}
	
	@Override
	public long f11DatagridCount(Map<String, Object> map) {
		String year=map.get("year")==null?"%":map.get("year").toString();
		String compqtype=map.get("compqtype")==null?"%":"%"+map.get("compqtype").toString()+"%";
		String communityname=map.get("communityname")==null?"%":"%"+map.get("communityname").toString()+"%";
		String ownername=map.get("ownername")==null?"%":"%"+map.get("ownername").toString()+"%";
		String buildNo=map.get("buildNo")==null?"%":"%"+map.get("buildNo").toString()+"%";
		String unitNo=map.get("unitNo")==null?"%":"%"+map.get("unitNo").toString()+"%";
		String floorNo=map.get("floorNo")==null?"%":"%"+map.get("floorNo").toString()+"%";
		
		String sql="select  count(*)  "
				+ "from view_qiyeapp "
				+ "where substr(chargemonth,1,4)=? "
				+ "and compqtype like ? and communityname like ?  "
				+ "and ownername like ? and substr(housecode,6,3) like ?  "
				+ "and substr(housecode,10,2) like ? and substr(housecode,13,2) like ?";
		return simple.queryForLong(sql, year,compqtype,communityname,ownername,buildNo,unitNo,floorNo);
	}
	
	@Override
	public Map<String, Object> f11DataRb(String meterserialno) {
		String sql="select * from (select commandtime,userid,meterserialno,flow,totalflow,totalheat,FEADWATERTEMPERATURE,RETURNWATERTEMPERATURE,warning,userstatus "
				+ "from rms.yhrl_record t where meterserialno=? order by commandtime desc) where rownum=1";
		try {
			return  simpleRb.queryForMap(sql, "1111"+meterserialno);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
	}
	

	@Override
	public List<Map<String, Object>> f13Datagrid(Date date) {
		String hql="select new map(" +
				"date_format(c.date,'%Y-%m-%d %h:%i:%s') as date," +
				"u.name as name," +
				"d.name as dname," +
				"c.houseCode as houseCode," +
				"c.address as address," +
				"c.jcResult as jcResult," +
				"c.jc as jc," +
				"c.kg as kg) " +
			"from CodeTag c "
			+ "left join c.user u "
			+ "left join u.dept d where date_format(c.date,'%Y-%m-%d')=date_format(?,'%Y-%m-%d')";
		return baseDao.query(hql, new java.sql.Date(date.getTime()));
	}

	@Transactional
	@Override
	public void f13JcSave(String account, String jc, String jcResult, String houseCode, String address) {
		User user=baseDao.getById(User.class,account);
		CodeTag codeTag=new CodeTag();
		codeTag.setDate(new Date());
		codeTag.setJc(jc);
		codeTag.setJcResult(jcResult);
		codeTag.setUser(user);
		codeTag.setAddress(address);
		codeTag.setHouseCode(houseCode);
		baseDao.save(codeTag);
		
	}

	@Transactional
	@Override
	public void f13KgSave(String account, String kg, String houseCode, String address) {
		User user=baseDao.getById(User.class,account);
		CodeTag codeTag=new CodeTag();
		codeTag.setDate(new Date());
		codeTag.setKg(kg);
		codeTag.setUser(user);
		codeTag.setAddress(address);
		codeTag.setHouseCode(houseCode);
		baseDao.save(codeTag);
		
	}
	
	@Override
	public List<Map<String, Object>> f41Datagrid(String filter) {
		String hql="select new map(" +
				"u.account as account," +
				"u.password as password," +
				"d.did as did," +
				"d.name as dname," +
				"u.name as name," +
				"u.phone as phone) " +
			"from User u left join u.dept d where u.type=1";
		List<Map<String, Object>> d=baseDao.query(hql);
		for (Map<String, Object> map : d) {
			User user=baseDao.getById(User.class,map.get("account").toString());
			List<String> funIds=new ArrayList<>();
			List<String> funNames=new ArrayList<>();
			for (Function fun: user.getFunctions()) {
				funIds.add(String.valueOf(fun.getFid()));
				funNames.add(fun.getName());
			}
			map.put("funIds", CommonUtil.collectionToString(funIds));
			map.put("funNames", CommonUtil.collectionToString(funNames));
		}
	return d;
	}

	
	@Transactional
	@Override
	public void f41DatagridSave(Map<String, Object> map) {
			User user=baseDao.getById(User.class,map.get("account").toString());
			Dept dept=baseDao.getById(Dept.class,Integer.parseInt(map.get("dept").toString()));
			if(user==null){user=new User();user.setAccount(map.get("account").toString());}
			user.setName(map.get("name").toString());
			user.setPassword(MD5Util.md5(map.get("password").toString()));
			user.setPhone(map.get("phone").toString());
			List<String> s=CommonUtil.StringToList(map.get("funIds").toString());
			Set<Function> funs=new HashSet<>();
			for (String string : s) {
				Function fun=baseDao.getById(Function.class,Integer.parseInt(string));
				funs.add(fun);
			}
			user.setFunctions(funs);
			user.setDept(dept);
			baseDao.saveOrUpdate(user);
		
	}
	
	@Transactional
	@Override
	public void f41DatagridDelete(String[] accounts) {
		for (String account : accounts) {
			baseDao.deleteById(User.class, account);
		}
	}
	
	

	@Override
	public List<Map<String, Object>> f42Datagrid(String filter) {
		String hql="select new map("+
				"did as did," +
				"name as dname) " +
			"from Dept";
		return baseDao.query(hql);
	}


	@Transactional
	@Override
	public String f42DatagridSave(Map<String, Object> map) {
		Dept dept=null;
		if(map.get("did")==null){dept=new Dept();}else
		dept=baseDao.getById(Dept.class,Integer.parseInt(map.get("did").toString()));
		dept.setName(map.get("dname").toString());
		baseDao.saveOrUpdate(dept);
		return null;
	}

	@Transactional
	@Override
	public String f42DatagridDelete(String[] depts) {
		for (String dept : depts) {
			baseDao.deleteById(Dept.class, Integer.parseInt(dept));
		}
		return null;
	}


	@Override
	public List<Map<String, Object>> f43Datagrid(String filter) {
		String hql="select new map("+
				"fid as fid," +
				"name as fname,"
				+ "pfid as pfid) " +
			"from Function";
		return baseDao.query(hql);
	}

	@Transactional
	@Override
	public String f43DatagridSave(Map<String, Object> map) {
		Function fun=baseDao.getById(Function.class,Integer.parseInt(map.get("fid").toString()));;
		if(fun==null){fun=new Function();fun.setFid(Integer.parseInt(map.get("fid").toString()));}
		fun.setName(map.get("fname").toString());
		fun.setPfid(Integer.parseInt(map.get("pfid").toString()));
		baseDao.saveOrUpdate(fun);
		return null;
	}

	@Transactional
	@Override
	public String f43DatagridDelete(String[] funs) {
		for (String fun : funs) {
			baseDao.deleteById(Function.class, Integer.parseInt(fun));
		}
		return null;
	}
	


	@Override
	public int login(String username, String password) {
		User user=baseDao.getById(User.class,username);
		if(user==null){return 2;}else
		if(user.getPassword().equals(MD5Util.md5(password)))return 1; else{return 3;}
		
	}
	
	@Override
	public Set<Function> getFunctions(String username) {
		User user=baseDao.getById(User.class,username);
		return user.getFunctions();
	}
	
	@Override
	public Object getUserInfo(String username) {
		User user=baseDao.getById(User.class,username);
		return user;
	}


	@Override
	public List<ComboBox> getComboBoxFunctions() {
		String sql="select CAST(f.fid AS CHAR) as 'value',CAST(f.name AS CHAR) as text,CAST(f.pfid AS CHAR) as 'desc' from yihe_meta_function f where f.type=2";
		List<ComboBox> funs=baseDao.queryBySql(sql, ComboBox.class);
		return funs;
	}
	
	@Override
	public List<ComboBox> getComboBoxDepts() {
		String sql="select CAST(did AS CHAR) as 'value',CAST(name AS CHAR) as text,CAST(type AS CHAR) as 'desc' from yihe_meta_dept";
		List<ComboBox> funs=baseDao.queryBySql(sql, ComboBox.class);
		return funs;
	}
	@Override
	public List<ComboBox> getComboBoxFunctionsLevel1() {
		String sql="select CAST(f.fid AS CHAR) as 'value',CAST(f.name AS CHAR) as text,CAST(f.pfid AS CHAR) as 'desc' from yihe_meta_function f where f.type=1";
		List<ComboBox> funs=baseDao.queryBySql(sql, ComboBox.class);
		return funs;
	}

	
	


	
}
