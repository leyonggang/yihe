package cn.ayit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ayit.dao.LocationDao;
import cn.ayit.domain.Location;

@Service
public class LocationService {

	@Transactional
	public void addLocation(Location location){
		this.locationDao.insertLocation(location);
	}
	
	@Transactional
	public List<Location> findLocation(String account,Date beginTime,Date endTime){
		List<Location> ls = locationDao.selectLocation(account, beginTime,endTime);
		return ls;
	}
	@Transactional
	public Location findLastLocation(String account){
		Location location = this.locationDao.selectLastLocation(account);
		return location;
	}
	
	@Autowired
	private LocationDao locationDao;

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	
}
