package cn.ayit.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.ayit.core.BaseAction;
import cn.ayit.domain.Location;
import cn.ayit.service.LocationService;

@Controller
@Scope("prototype")
public class LocationAction extends BaseAction implements ModelDriven<Location> {

	private static final long serialVersionUID = 1L;

	private Location location = new Location();

	public String locationAdd() {
		dataMap.clear();
		try {
			Date now = new Date();
			//java.sql.Date today = new java.sql.Date(now.getTime());
			//java.sql.Time time = new java.sql.Time(now.getTime());
			//location.setLocationDate(today);
			//location.setLocationTime(time);
			location.setLocationTime(now);
			this.locationService.addLocation(location);
			dataMap.put("msg", "1");
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}

	public String locationFind() {

		dataMap.clear();

		String account = this.getRequest().getParameter("account");
		String beginTimeStr = this.getRequest().getParameter("beginTime");
		String endTimeStr = this.getRequest().getParameter("endTime");
		
		System.out.println(account+"=="+beginTimeStr+"=="+endTimeStr);
	
		if (account == null || account.equals("")) {
			dataMap.put("msg", "用户编码为空！");
			return SUCCESS;
		}
		
		if(beginTimeStr==null ||beginTimeStr.equals("")){
			dataMap.put("msg", "开始时间不能为空！");
			return SUCCESS;
		}
		
		if(endTimeStr==null || endTimeStr.equals("")){
			dataMap.put("msg", "截止时间不能为空！");
			return SUCCESS;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime = null;
		Date endTime = null;
		
		try {
			beginTime = sdf.parse(beginTimeStr);
			endTime = sdf.parse(endTimeStr);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		dataMap.clear();
		try {
			List<Location> ls = this.locationService.findLocation(account,beginTime,endTime);
			dataMap.put("msg", "1");
			dataMap.put("ls", ls);
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		
		return SUCCESS;

	}

	public String locationFindLast() {

		String account = this.getRequest().getParameter("account");
		if (account == null || account == "") {
			dataMap.put("msg", "用户编号为空！");			
		}
		try {
			Location location = this.locationService.findLastLocation(account);
			if (location != null) {
				dataMap.put("msg", "1");
				dataMap.put("location", location);
			} else {
				dataMap.put("msg", "没有此人位置信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}

	@Autowired
	private LocationService locationService;

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	@Override
	public Location getModel() {
		return this.location;
	}

}
