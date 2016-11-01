package cn.ayit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ayit.dao.AttendanceDao;
import cn.ayit.domain.Attendance;

@Service
public class AttendanceService {
	
	@Transactional
	public void addAttendance(Attendance attendance){
		attendance.setAttendTime(new Date());
		this.attendanceDao.insertAttendace(attendance);
	}
	@Transactional
	public List<Attendance> findAttendance(String name,String beginTime,String endTime){
		
		/*if(beginTime!=null && !beginTime.equals("")){
			beginTime=beginTime+" 00:00:00";
		}
		if(endTime!=null &&!endTime.equals("")){
			endTime = endTime+" 24:00:00";
		}*/
		
		
		List<Attendance> ls = this.attendanceDao.selectAttendace(name, beginTime, endTime);
		return ls;
	}
	
	@Autowired
	private AttendanceDao attendanceDao;

	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}
}
