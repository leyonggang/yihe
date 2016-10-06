package cn.ayit.service.impl;

import net.sf.ezmorph.bean.MorphDynaBean;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import cn.ayit.dao.BaseDao;
import cn.ayit.domain.Dept;
import cn.ayit.domain.User;
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


	@Override
	public List<Map<String, Object>> f41Datagrid(String filter) {
		String hql="select new map(" +
				"u.account as account," +
				"u.password as password," +
				"d.did as did," +
				"u.name as name," +
				"u.phone as phone) " +
			"from User u left join u.dept d";
	return baseDao.query(hql);
	}


	@Transactional
	@Override
	public void f41DatagridSave(Map<String, Object> map) {
		System.out.println(map);
			User user=baseDao.getById(User.class,map.get("account").toString());
			Dept dept=baseDao.getById(Dept.class,Integer.parseInt(map.get("dept").toString()));
			if(user==null){user=new User();user.setAccount(map.get("account").toString());}
			user.setName(map.get("name").toString());
			user.setPassword(map.get("password").toString());
			user.setPhone(map.get("phone").toString());
			user.setDept(dept);
			baseDao.saveOrUpdate(user);
		
	}

	@Transactional
	@Override
	public void f41DatagridDelete(String[] accounts) {

		for (String account : accounts) {
			System.out.println(account);
			baseDao.deleteById(User.class, account);
		}
	}
}
