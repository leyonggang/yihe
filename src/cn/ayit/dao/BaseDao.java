package cn.ayit.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface BaseDao {

	public void sessionFlush();
	//从session一级缓存清除所有对象
	public void sessionClear();
	//从session一级缓存清除某一个对象
	public void sessionEvict(Object obj);
	
	public <T> Serializable save(T o);
	public <T> void saveOrUpdate(T o);
	public <T> T merge(T o);
	
	public <T> void delete(T o);
	public <T> void deleteById(Class<T> c,Serializable id);
	public <T> void update(T o);
	
	public <T> T getById(Class<T> c, Serializable id);
	public <T> T getByHql(String hql,Object ...params);
	public Map<String, Object>  getBySql(String sql,Object ...params);
	public <T> T getByHql(String hql, Map<String, Object> params);
	
	
	public <T> List<T> query(String hql,Object ...params);
	public <T> List<T> query(String hql, Map<String, Object> params);
	public <T> List<T> queryPage(String hql, int page, int rows, Object ...params);
	public <T> List<T> queryPage(String hql, int page, int rows, Map<String, Object> params);

	public <T> List<T> queryBySql(String sql,Class<T> c,Object ...params);
	public <T> List<T> queryBySql(String sql,Class<T> c,Map<String, Object> params);
	public <T> List<T> queryPageBySql(String sql,Class<T> c, int page, int rows, Object ...params);
	public <T> List<T> queryPageBySql(String sql,Class<T> c, int page, int rows, Map<String, Object> params);
	
	public List<Map<String,Object>> queryBySqlForMap(String sql,Object ...params);
	public List<Map<String,Object>> queryBySqlForMap(String sql, Map<String, Object> params);
	public List<Map<String,Object>> queryPageBySqlForMap(String sql, int page, int rows,Object ...params);
	public List<Map<String,Object>> queryPageBySqlForMap(String sql, Map<String, Object> params, int page, int rows);

	public Long count(String hql,Object ...params);
	public Long count(String hql, Map<String, Object> params);
	
	public int executeHql(String hql,Object ...params);
	public int executeHql(String hql, Map<String, Object> params);
	
	public int executeSql(String sql,Object ...params);
	public int executeSql(String sql, Map<String, Object> params);
	
	public Integer countBySql(String sql,Object ...params);
	public Integer countBySql(String sql, Map<String, Object> params);
	
}
