package cn.ayit.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;

import cn.ayit.core.BaseAction;
import cn.ayit.domain.Attendance;
import cn.ayit.service.AttendanceService;

@Component
@Scope("prototype")
public class AttendanceAction extends BaseAction implements ModelDriven<Attendance> {

	
	private static final long serialVersionUID = 1L;

	public String attendanceAdd() {
		dataMap.clear();
		try {			
			this.attendanceService.addAttendance(attendance);
			dataMap.put("msg", "1");
		} catch (Exception e) {
			dataMap.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String attendanceFind(){
		String name = this.getRequest().getParameter("name");
		String beginTime = this.getRequest().getParameter("benginTime");
		String endTime = this.getRequest().getParameter("endTime");
		
		if((name==null|| name.equals("")) &&(beginTime==null || beginTime.equals(""))&&(endTime==null || endTime.equals(""))){
			dataMap.put("ls", null);		
			return SUCCESS;
		}
		
		try{
			List<Attendance> ls = this.attendanceService.findAttendance(name, beginTime, endTime);
			dataMap.put("msg", "1");
			dataMap.put("ls", ls);
		}catch(Exception e){
			dataMap.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	private Attendance attendance;

	@Override
	public Attendance getModel() {
		// TODO Auto-generated method stub
		return attendance;
	}

	@Autowired
	private AttendanceService attendanceService;

	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
}
