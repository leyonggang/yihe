package cn.ayit.domain;

public class OrderReport {
	private int orderCount;//派单个数
	
	private int processCount;//待处理个数
	private int delayCount;//已延时个数
	private int archiveCount;//咨询归档个数
	private int overCount;//处理完毕个数
	
	private int statisfyCount;//满意个数
	private int noStatisfyCount; //不满意个数
	
	private int okCount; //问题解决个数
	private int noOkCount; //问题有遗留个数
	
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public int getProcessCount() {
		return processCount;
	}
	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}
	public int getDelayCount() {
		return delayCount;
	}
	public void setDelayCount(int delayCount) {
		this.delayCount = delayCount;
	}
	public int getArchiveCount() {
		return archiveCount;
	}
	public void setArchiveCount(int archiveCount) {
		this.archiveCount = archiveCount;
	}
	public int getOverCount() {
		return overCount;
	}
	public void setOverCount(int overCount) {
		this.overCount = overCount;
	}
	public int getStatisfyCount() {
		return statisfyCount;
	}
	public void setStatisfyCount(int statisfyCount) {
		this.statisfyCount = statisfyCount;
	}
	public int getNoStatisfyCount() {
		return noStatisfyCount;
	}
	public void setNoStatisfyCount(int noStatisfyCount) {
		this.noStatisfyCount = noStatisfyCount;
	}
	public int getOkCount() {
		return okCount;
	}
	public void setOkCount(int okCount) {
		this.okCount = okCount;
	}
	public int getNoOkCount() {
		return noOkCount;
	}
	public void setNoOkCount(int noOkCount) {
		this.noOkCount = noOkCount;
	}
	
}
