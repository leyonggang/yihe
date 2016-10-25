package cn.ayit.action;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ayit.domain.Function;
import cn.ayit.domain.data.ComboBox;
import cn.ayit.service.DataService;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

@Controller("dataGridAction")
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")
@Action(value = "dataGrid", results = @Result(name = "success", type = "json", params = {
		"includeProperties", "mes,row.*,lista.*,obja.*" }))
public class DataGridAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(DataGridAction.class);
	

	@Resource
	private DataService dataService;
	private String json;
	private String stra;
	private String strb;
	private String strc;
	private String strd;
	private String filter;
	private Date datea;
	private int inta;
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String mes;
	private String[] arrya;
	private List<Map<String, Object>> objs=new ArrayList<Map<String,Object>>();
	private Collection<Object> lista;
	private Object obja;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	

	public String login() throws Exception {
		int r=dataService.login(stra, strb);
		if(r==1){
			mes="suc";
			obja=dataService.getUserInfo(stra);
			ServletActionContext.getRequest().getSession().setAttribute("user", obja);
		} else if(r==2){
			mes="用户不存在";
		} else {
			mes="密码错误";	
		}
		return SUCCESS;
	}
	
	public String logout() throws Exception {
		try {
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.invalidate();
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();
		}
		
		
		return SUCCESS;
	}
	
	public String setPassword() throws Exception {
		try {
			dataService.setPassword(stra,strb);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();
		}
		
		
		return SUCCESS;
	}
	
	public String userInfo() throws Exception {
		obja=ServletActionContext.getRequest().getSession().getAttribute("user");
		mes="suc";
		return SUCCESS;
	}
	
	
	public String getComboBoxFunctions() throws Exception {
		List<ComboBox> funs=dataService.getComboBoxFunctions();
		lista=new ArrayList<Object>(funs);
		mes="suc";
		return SUCCESS;
	}
	
	public String getComboBoxFunctionsLevel1() throws Exception {
		List<ComboBox> funs=dataService.getComboBoxFunctionsLevel1();
		lista=new ArrayList<Object>(funs);
		mes="suc";
		return SUCCESS;
	}
	
	public String getComboBoxDepts() throws Exception {
		List<ComboBox> funs=dataService.getComboBoxDepts();
		lista=new ArrayList<Object>(funs);
		mes="suc";
		return SUCCESS;
	}
	
	
	public String f11Datagrid() throws Exception {
		JSONObject jsonObj=JSONObject.fromObject(json);
       	Map<String, Object> map=(Map) JSONObject.toBean(jsonObj, Map.class);
		List<Map<String, Object>> es=dataService.f11Datagrid(map);
		long cnt=dataService.f11DatagridCount(map);
		if(es!=null)objs=es;mes="suc";obja=cnt;
		return SUCCESS;
	}
	
	public String f11DataRb() throws Exception {
		Map<String, Object> es=dataService.f11DataRb(stra);
		if(es!=null)obja=es;mes="suc";
		return SUCCESS;
	}

	public String f13JcSave() throws Exception {
		try {
			dataService.f13JcSave(stra,strb);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;	
	}
	
	public String f13KgSave() throws Exception {
		try {
			dataService.f13KgSave(stra,strb);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;	
	}
	
	
	public String f41Datagrid() throws Exception {
		List<Map<String, Object>> es=dataService.f41Datagrid(filter);
		if(es!=null)objs=es;mes="suc";
		return SUCCESS;
	}
	
	
	public String f41DatagridSave() throws Exception {
		try {
			JSONObject jsonObj=JSONObject.fromObject(json);
	       	Map<String, Object> map=(Map) JSONObject.toBean(jsonObj, Map.class);
			dataService.f41DatagridSave(map);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	public String f41DatagridDelete() throws Exception {
		try {
			dataService.f41DatagridDelete(arrya);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	
	
	public String f42DatagridSave() throws Exception {
		try {
			JSONObject jsonObj=JSONObject.fromObject(json);
	       	Map<String, Object> map=(Map) JSONObject.toBean(jsonObj, Map.class);
			dataService.f42DatagridSave(map);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	public String f42DatagridDelete() throws Exception {
		try {
			dataService.f42DatagridDelete(arrya);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	public String f43DatagridSave() throws Exception {
		try {
			JSONObject jsonObj=JSONObject.fromObject(json);
	       	Map<String, Object> map=(Map) JSONObject.toBean(jsonObj, Map.class);
			dataService.f43DatagridSave(map);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	public String f43DatagridDelete() throws Exception {
		try {
			dataService.f43DatagridDelete(arrya);
			mes="suc";
		} catch (Exception e) {
			e.printStackTrace();
			mes=e.getMessage();return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getStra() {
		return stra;
	}

	public void setStra(String stra) {
		this.stra = stra;
	}

	public String getStrb() {
		return strb;
	}

	public void setStrb(String strb) {
		this.strb = strb;
	}

	public Date getDatea() {
		return datea;
	}

	public void setDatea(Date datea) {
		this.datea = datea;
	}

	public int getInta() {
		return inta;
	}

	public void setInta(int inta) {
		this.inta = inta;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@JSON(name="rows",format="yyyy-MM-dd")
	public List<Map<String, Object>> getObjs() {
		return objs;
	}
	public void setObjs(List<Map<String, Object>> objs) {
		this.objs = objs;
	}
	public String getStrc() {
		return strc;
	}
	public void setStrc(String strc) {
		this.strc = strc;
	}
	public String getStrd() {
		return strd;
	}
	public void setStrd(String strd) {
		this.strd = strd;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	@JSON
	public Collection<Object> getLista() {
		return lista;
	}

	public void setLista(Collection<Object> lista) {
		this.lista = lista;
	}
	
	@JSON(format="yyyy-MM-dd")
	public Object getObja() {
		return obja;
	}

	public void setObja(Object obja) {
		this.obja = obja;
	}
	
	public String[] getArrya() {
		return arrya;
	}

	public void setArrya(String[] arrya) {
		this.arrya = arrya;
	}
}
