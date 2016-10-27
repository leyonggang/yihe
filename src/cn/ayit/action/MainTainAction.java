package cn.ayit.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ayit.core.BaseAction;
import cn.ayit.domain.MainTain;
import cn.ayit.domain.OrderReport;
import cn.ayit.service.MainTainService;

@Scope("prototype")
@Controller
public class MainTainAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	public String mainTainFindWorkerName(){
		dataMap.clear();
		String workerName = this.getRequest().getParameter("workerName");
		String  beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		
		if(workerName==null || workerName .equals("")){
			dataMap.put("msg", "维修人员名称不能为空");
			return SUCCESS;
		}
		
		if(beginDate==null || beginDate.equals("")){
			dataMap.put("msg", "起始日期不能为空");
			return SUCCESS;
		}
		
		if(beginDate==null || beginDate.equals("")){
			dataMap.put("msg", "结束日期不能为空");
			return SUCCESS;
		}
		
		try{
			List<MainTain> ls = this.mainTainService.findMainTainWorker(workerName, beginDate, endDate);
			dataMap.put("msg", "1");
			dataMap.put("ls", ls);
		}catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String mainTainFindCommunity(){
		dataMap.clear();
		String communityName = this.getRequest().getParameter("communityName");
		String  beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		
		if(communityName==null || communityName .equals("")){
			dataMap.put("msg", "小区名称不能为空");
			return SUCCESS;
		}
		
		if(beginDate==null || beginDate.equals("")){
			dataMap.put("msg", "起始日期不能为空");
			return SUCCESS;
		}
		
		if(beginDate==null || beginDate.equals("")){
			dataMap.put("msg", "结束日期不能为空");
			return SUCCESS;
		}
		
		try{
			List<MainTain> ls = this.mainTainService.findMainTainCommunity(communityName, beginDate, endDate);
			dataMap.put("msg", "1");
			dataMap.put("ls", ls);
		}catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	
	public String mainTainReportCommunity(){
		dataMap.clear();
		String communityName = this.getRequest().getParameter("communityName");
		String  beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
					
		try{
			OrderReport orderReport = this.mainTainService.findOrderReportCommunity(communityName, beginDate, endDate);
			dataMap.put("msg", "1");
			dataMap.put("orderReport", orderReport);
		}catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	
	public String mainTainReportWorker(){
		dataMap.clear();
		String workerName = this.getRequest().getParameter("workerName");
		String  beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		
		
		try{
			OrderReport orderReport = this.mainTainService.findOrderReportWorker(workerName, beginDate, endDate);
			dataMap.put("msg", "1");
			dataMap.put("orderReport", orderReport);
		}catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String mainTainReportCommunityDetails(){
		dataMap.clear();
		String communityName = this.getRequest().getParameter("communityName");
		String  beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		String type = this.getRequest().getParameter("type");
		
		
		try{
			List<MainTain> ls = this.mainTainService.findMainTainCommunity(communityName, beginDate, endDate);
			List<MainTain> details = findDetails(ls, type);
			dataMap.put("msg", "1");
			dataMap.put("details", details);			
		}catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		
		
		
		return SUCCESS;
				
	}
	
	public String mainTainReportWorkerDetails(){
		dataMap.clear();
		String workerName = this.getRequest().getParameter("workerName");
		String  beginDate = this.getRequest().getParameter("beginDate");
		String endDate = this.getRequest().getParameter("endDate");
		String type = this.getRequest().getParameter("type");

		
		try{
			List<MainTain> ls = this.mainTainService.findMainTainWorker(workerName, beginDate, endDate);
			List<MainTain> details = findDetails(ls, type);
			dataMap.put("msg", "1");
			dataMap.put("details", details);			
		}catch (Exception e) {
			e.printStackTrace();
			dataMap.put("msg", e.getMessage());
		}
		return SUCCESS;
	}
	private List<MainTain> findDetails(List<MainTain> ls,String type){
		List<MainTain> details = new ArrayList<MainTain>();

		if("1".equals(type)){
			for(MainTain mainTain : ls){				
					details.add(mainTain);			
			}			
		}else if("2".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_DEALRESULT().equals("待处理")){
					details.add(mainTain);
				}
			}
		}else if("3".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_DEALRESULT().equals("已延时")){
					details.add(mainTain);
				}
			}
		}else if("4".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_DEALRESULT().equals("咨询归档")){
					details.add(mainTain);
				}
			}
		}else if("5".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_DEALRESULT().equals("处理完毕")){
					details.add(mainTain);
				}
			}
		}else if("6".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_backManner().equals("满意")){
				details.add(mainTain);
			}
			}
			
		}else if("7".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_backManner().equals("不满意")){
					details.add(mainTain);
				}
			}
		}else if("8".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_backQuality().equals("问题解决")){
					details.add(mainTain);
				}
			}
		}else if("9".equals(type)){
			for(MainTain mainTain : ls){
				if(mainTain.getM_backQuality().equals("问题有遗留")){
					details.add(mainTain);
				}
			}
		}
		
		return details;
	}
	
	
	@Autowired
	private MainTainService mainTainService;

	public void setMainTainService(MainTainService mainTainService) {
		this.mainTainService = mainTainService;
	}
	
	

}
