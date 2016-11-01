package cn.ayit.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.ayit.core.HibernateDao;
import cn.ayit.domain.Attendance;

@Repository
public class AttendanceDao extends HibernateDao{
	
	public void insertAttendace(Attendance attendance){
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(attendance);
	}
	

	public List<Attendance> selectAttendace(String name ,String beginTime, String endTime){
		String hql="select new Attendance(a.id as id,a.account as account,a.attendTime as attendTime,a.type as type,u.name as name) from Attendance a , User u where a.account=u.account";
		StringBuilder sb = new StringBuilder(hql);
	    
		if(name!=null && !name.equals("")){
			sb.append(" and u.name='").append(name).append("'");
		}
		if(beginTime!=null && !beginTime.equals("")){
			sb.append(" and a.attendTime>='").append(beginTime).append(" 00:00:00").append("'");
		}
		if(endTime!=null && !endTime.equals("")){
			sb.append(" and a.attendTime<='").append(endTime).append(" 24:00:00").append("'");
		}
		
		return baseDao.query(sb.toString());		
	}
	
	@Resource
	BaseDao baseDao;
	
}
