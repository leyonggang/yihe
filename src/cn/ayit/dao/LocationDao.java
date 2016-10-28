package cn.ayit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.ayit.core.HibernateDao;
import cn.ayit.domain.Location;

@Repository
public class LocationDao extends HibernateDao{

	public void insertLocation(Location location){
	    Session session = this.getSessionFactory().getCurrentSession();

		session.save(location);
	}
	
	public List<Location> selectLocation(String account, Date beginTime,Date endTime){
	    Session session = this.getSessionFactory().getCurrentSession();
	    Query query = session.createQuery("from Location l where l.account=? and l.locationTime>? and l.locationTime<?");
	    query.setString(0, account);
	    query.setTimestamp(1,beginTime);
	    query.setTimestamp(2, endTime);
	    List<Location> ls = query.list();
	    System.out.println("ls.size==="+ls.size());
	    return ls;
	}
	
	public Location selectLastLocation(String account){
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Location x where x.id=(select max(y.id) from Location y where y.account=?)");
	    query.setString(0, account);
	    List<Location> ls = query.list();
	    if(ls.size()>0){
	    	return ls.get(0);
	    }else{
	    	return null;
	    }
	}
}
