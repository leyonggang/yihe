package cn.ayit.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public Map<String, Object> dataMap = new HashMap<String, Object>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	protected HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest req = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		return req;
	}
}
