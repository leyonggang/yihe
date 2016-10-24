package cn.ayit.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.ayit.domain.Function;
import cn.ayit.domain.data.ComboBox;


public interface DataService {
	
	public int login(String username,String password);
	public Set<Function> getFunctions(String username);
	public Object getUserInfo(String username);
	public void setPassword(String username, String password);
	
	//f11用户信息数据查询
	public List<Map<String, Object>> f11Datagrid(Map<String, Object> map);
	public long f11DatagridCount(Map<String, Object> map);
	public Map<String, Object> f11DataRb(String meterserialno);
	
	
	
	//维修，巡线人员列表
	public List<Map<String, Object>> findWeixiu(String string);
	public List<Map<String, Object>> findXunxian(String string);
	
	

	//f41用户管理
	public List<Map<String, Object>> f41Datagrid(String filter);
	public void f41DatagridSave(Map<String, Object> map);
	public void f41DatagridDelete(String[] account);
	
	
	//f42部门管理
	public List<Map<String, Object>> f42Datagrid(String filter);
	public String f42DatagridSave(Map<String, Object> map);
	public String f42DatagridDelete(String[] depts);
	
	//f43功能管理
	public List<Map<String, Object>> f43Datagrid(String filter);
	public String f43DatagridSave(Map<String, Object> map);
	public String f43DatagridDelete(String[] funs);

	
	//选择框数据列表
	public List<ComboBox> getComboBoxFunctions();
	public List<ComboBox> getComboBoxDepts();
	public List<ComboBox> getComboBoxFunctionsLevel1();
	
	
	
	
	
}
