package cn.ayit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.ayit.core.HibernateDao;
import cn.ayit.domain.MainTain;

@Repository
public class MainTainDao extends HibernateDao {

	public List<MainTain> findMainTainWorker(String workerName, String beginDate, String endDate){
		
		String sql ="select * from VIEW_MAINTAIN where M_DEALOPERATOR=:workerName and substr(M_DISPATCHTIME,0,10)>=:beginDate and substr(M_DISPATCHTIME,0,10)<=:endDate";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workerName", workerName);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		List<MainTain> ls = this.jdbcTemplate.query(sql, new MainTainRowMapper(),map);
		return ls;
	}
	
	public List<MainTain> findMainTainCommunity(String communityName,String beginDate, String endDate){
		LogFactory.getLog(this.getClass()).debug(communityName+","+beginDate+","+endDate);
		
		String sql ="select * from VIEW_MAINTAIN where instr(m_address,:communityName)>0 and substr(M_DISPATCHTIME,0,10)>=:beginDate and substr(M_DISPATCHTIME,0,10)<=:endDate";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("communityName", communityName);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		List<MainTain> ls = this.jdbcTemplate.query(sql, new MainTainRowMapper(),map);
		LogFactory.getLog(this.getClass()).debug(ls.size());
		return ls;
	}
	

	/*public List<MainTain> findMainTain(int userId, Date beginDate, Date endDate) {
		Session session = this.getSessionFactory().getCurrentSession();

		Query query = session.createQuery("") ;
		query.setInteger(0, userId);
		query.setDate(1, beginDate);
		query.setDate(2, endDate);
		
	}*/
		
		
		private class MainTainRowMapper implements RowMapper<MainTain>{

			@Override
			public MainTain mapRow(ResultSet rs, int arg1) throws SQLException {
				MainTain mainTain = new MainTain();
				mainTain.setM_code(rs.getString("M_code"));
				mainTain.setM_HANDTYPE(rs.getString("M_HANDTYPE"));
				mainTain.setM_DEALOPERATOR(rs.getString("M_DEALOPERATOR"));
				mainTain.setM_DISPATCHTIME(rs.getString("M_DISPATCHTIME"));
				mainTain.setM_DEALTIME(rs.getString("M_DEALTIME"));
				mainTain.setM_ADDRESS(rs.getString("M_ADDRESS"));
				mainTain.setM_LINKMAN(rs.getString("M_LINKMAN"));
				mainTain.setM_DISPATCHCONTENT( rs.getString("M_DISPATCHCONTENT"));
				mainTain.setM_DEALCONTENT(rs.getString("M_DEALCONTENT"));
				mainTain.setM_DEALRESULT( rs.getString("M_DEALRESULT"));
				mainTain.setM_backtime( rs.getString("M_backtime"));
				mainTain.setM_backManner(rs.getString("M_backManner"));
				mainTain.setM_backQuality(rs.getString("M_backQuality"));

				return mainTain;
			}
			
		}

	@Resource(name="simpleJdbcTemplate")
	private SimpleJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
