package cn.ayit.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.ayit.dao.BaseDao;

@Transactional
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {
		
	@Resource
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public void sessionFlush() {
		getCurrentSession().flush();
	}
	
	@Override
	public void sessionClear() {
		getCurrentSession().clear();
	}

	@Override
	public void sessionEvict(Object obj) {
		getCurrentSession().evict(obj);
	}
	@Override
	public <T> Serializable save(T o) {
		if (o != null) {
			return getCurrentSession().save(o);
		}
		return null;
	}

	@Override
	public <T> void saveOrUpdate(T o) {
		if (o != null) {
			getCurrentSession().saveOrUpdate(o);
		}
	}

	@Override
	public <T> T merge(T o) {
		if (o != null) {
			return (T) getCurrentSession().merge(o);
		}
		return null;
	}

	@Override
	@Transactional(noRollbackFor={ObjectNotFoundException.class})
	public <T> void delete(T o) {
		if (o != null) {
			getCurrentSession().delete(o);
		}
	}

	@Override
	@Transactional(noRollbackFor={ObjectNotFoundException.class})
	public <T> void deleteById(Class<T> c, Serializable id) {
		T t=getById(c, id);
		if(t!=null) getCurrentSession().delete(t);
	}

	@Override
	public <T> void update(T o) {
		if (o != null) {
			getCurrentSession().update(o);
		}
	}

	@Override
	public <T> T getById(Class<T> c, Serializable id) {
		return (T) getCurrentSession().get(c, id);
	}

	@Override
	public <T> T getByHql(String hql, Object... params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> getBySql(String sql, Object... params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<Map<String, Object>> l = q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	@Override
	public <T> T getByHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> query(String hql, Object... params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.list();
	}

	@Override
	public <T> List<T> query(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public <T> List<T> queryPage(String hql, int page, int rows,
			Object... params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public <T> List<T> queryPage(String hql, int page, int rows,
			Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public <T> List<T> queryBySql(String sql, Class<T> c, Object... params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.setResultTransformer(Transformers.aliasToBean(c)).list();
	}

	@Override
	public <T> List<T> queryBySql(String sql, Class<T> c,
			Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setResultTransformer(Transformers.aliasToBean(c)).list();
	}

	@Override
	public <T> List<T> queryPageBySql(String sql, Class<T> c, int page,
			int rows, Object... params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.aliasToBean(c)).list();
	}

	@Override
	public <T> List<T> queryPageBySql(String sql, Class<T> c, int page,
			int rows, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.aliasToBean(c)).list();
	}

	@Override
	public List<Map<String, Object>> queryBySqlForMap(String sql,
			Object... params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map<String, Object>> queryBySqlForMap(String sql,
			Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map<String, Object>> queryPageBySqlForMap(String sql, int page,
			int rows,Object ...params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map<String, Object>> queryPageBySqlForMap(String sql,
			Map<String, Object> params, int page, int rows) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public Long count(String hql, Object... params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql, Object... params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Object... params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer countBySql(String sql, Object... params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if(params.length!=0){
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return (Integer) q.uniqueResult();
	}

	@Override
	public Integer countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Integer) q.uniqueResult();
	}

}
