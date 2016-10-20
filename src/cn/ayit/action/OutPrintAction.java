package cn.ayit.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ayit.domain.data.ComboBox;
import cn.ayit.dao.BaseDao;
import cn.ayit.service.DataService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller("outPrintAction")
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
@Action(value = "outPrint", results = @Result(name = "success", type = "json"))
public class OutPrintAction extends ActionSupport implements Preparable{
	
	@Resource
	private DataService dataService;
	
	PrintWriter out;

	// 输入参数
	private String json;
	private String stra;
	private String strb;
	private Date datea;
	private int inta;


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	
	public String f11Datagrid() throws Exception {
		JSONObject jsonObj=JSONObject.fromObject(json);
       	Map<String, Object> map=(Map) JSONObject.toBean(jsonObj, Map.class);
		List<Map<String, Object>> es=dataService.f11Datagrid(map);
		JSONArray jsonarray=JSONArray.fromObject(es);
		out.print(jsonarray);
		return null;
	}
	
	public String f12Datagrid() throws Exception {
		String getURL ="http://112.53.100.152:8888/ForGongXueYuan/ForGongXueYuan.aspx?type=HRZ&PQ="+URLEncoder.encode(stra,"utf-8");
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String lines;
         while ((lines = reader.readLine()) != null) {
                 //System.out.println(lines);
                 out.append(lines);
         }
         reader.close();
         connection.disconnect();
		return null;
	}
	
	public String f42Datagrid() throws Exception {
		List<Map<String, Object>> es=dataService.f42Datagrid("");
		JSONArray jsonarray=JSONArray.fromObject(es);
		out.print(jsonarray);
		return null;
	}
	
	public String f43Datagrid() throws Exception {
		List<Map<String, Object>> es=dataService.f43Datagrid("");
		JSONArray jsonarray=JSONArray.fromObject(es);
		out.print(jsonarray);
		return null;
	}
	
	public String findXunxian() throws Exception {
		List<Map<String, Object>> es=dataService.findXunxian("");
		JSONArray jsonarray=JSONArray.fromObject(es);
		out.print(jsonarray);
		return null;
	}
	public String findWeixiu() throws Exception {
		List<Map<String, Object>> es=dataService.findWeixiu("");
		JSONArray jsonarray=JSONArray.fromObject(es);
		out.print(jsonarray);
		return null;
	}
	
	public String getComboBoxFunctions() throws Exception {
		//List<ComboBox> funs=dataService.getComboBoxFunctions();
		//JSONArray jsonarray=JSONArray.fromObject(funs);
		out.print("jsona([{name:\"John\"}])");
		return null;
	}
	
	public String getComboBoxDepts() throws Exception {
		List<ComboBox> funs=dataService.getComboBoxDepts();
		JSONArray jsonarray=JSONArray.fromObject(funs);
		out.print(jsonarray);
		return null;
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

	@Override
	public void prepare() throws Exception {
		HttpServletResponse res= ServletActionContext.getResponse();
		res.setContentType("text/html;charset=utf-8");
		out =res.getWriter();
	}
	
	

}
