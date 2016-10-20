package cn.ayit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ayit.dao.MainTainDao;
import cn.ayit.domain.MainTain;
import cn.ayit.domain.OrderReport;

@Service
public class MainTainService {
	public List<MainTain> findMainTainWorker(String workerName, String beginDate, String endDate) {
		List<MainTain> ls = this.mainTainDao.findMainTainWorker(workerName, beginDate, endDate);
		return ls;
	}

	public List<MainTain> findMainTainCommunity(String communityName, String beginDate, String endDate) {
		List<MainTain> ls = this.mainTainDao.findMainTainCommunity(communityName, beginDate, endDate);
		return ls;
	}

	public OrderReport findOrderReportWorker(String workerName, String beginDate, String endDate) {
		List<MainTain> ls = this.findMainTainWorker(workerName, beginDate, endDate);
		OrderReport orderReport = this.lsToReport(ls);
		return orderReport;
	}	
	
	public OrderReport findOrderReportCommunity(String communityName, String beginDate, String endDate) {
		List<MainTain> ls = this.findMainTainCommunity(communityName, beginDate, endDate);
		OrderReport orderReport = this.lsToReport(ls);
		return orderReport;
	}
	
	
	
	private OrderReport lsToReport(List<MainTain> ls){
		OrderReport orderReport = new OrderReport();

		for (MainTain mainTain : ls) {
			orderReport.setOrderCount(orderReport.getOrderCount() + 1);
			
			if (mainTain.getM_DEALRESULT() != null) {
				if (mainTain.getM_DEALRESULT().equals("待处理")) {
					orderReport.setProcessCount(orderReport.getProcessCount()+1);
				} else if (mainTain.getM_DEALRESULT().equals("已延时")) {
					orderReport.setDelayCount(orderReport.getDelayCount()+1);
				} else if (mainTain.getM_DEALRESULT().equals("咨询归档")) {
					orderReport.setArchiveCount(orderReport.getArchiveCount()+1);
				} else if (mainTain.getM_DEALRESULT().equals("处理完毕")) {
					orderReport.setOverCount(orderReport.getOverCount()+1);
				}
			}
			
			if(mainTain.getM_backManner()!=null){
				if(mainTain.getM_backManner().equals("满意")){
					orderReport.setStatisfyCount(orderReport.getStatisfyCount()+1);
				}else if(mainTain.getM_backManner().equals("不满意")){
					orderReport.setNoStatisfyCount(orderReport.getNoStatisfyCount()+1);

				}
			}
			
			if(mainTain.getM_backQuality()!=null){
				if(mainTain.getM_backQuality().equals("问题解决")){
					orderReport.setOkCount(orderReport.getOkCount()+1);
				}else if(mainTain.getM_backQuality().equals("问题有遗留")){
					orderReport.setNoOkCount(orderReport.getNoOkCount()+1);
				}
			}
			
		}
		return orderReport;
	}

	@Autowired
	private MainTainDao mainTainDao;

	public void setMainTainDao(MainTainDao mainTainDao) {
		this.mainTainDao = mainTainDao;
	}
}
