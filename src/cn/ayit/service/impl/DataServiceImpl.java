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
				"account as account," +
				"name as name," +
				"phone as phone) " +
			"from Users ";
	return baseDao.query(hql,filter);
	}

		
}
