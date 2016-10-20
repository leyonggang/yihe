package cn.ayit.utils;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonUtil {
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	   * 检测程序。 
	   *  
	   * @param processName 线程的名字，请使用准确的名字 
	   * @return 找到返回true,没找到返回false 
	   */  
	  public static boolean findProcess(String processName) {  
	    BufferedReader bufferedReader = null;  
	    try {  
	      Process proc = Runtime.getRuntime().exec("tasklist /fi  \"imagename eq "+processName+"\"");
	      bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));  
	      String line = null;  
	      while ((line = bufferedReader.readLine()) != null) {  
	        if (line.contains(processName)) {  
	          return true;  
	        }  
	      }  
	      return false;  
	    } catch (Exception ex) {  
	      ex.printStackTrace();  
	      return false;  
	    } finally {  
	      if (bufferedReader != null) {  
	        try {  
	          bufferedReader.close();  
	        } catch (Exception ex) {}  
	      }  
	    }  
	  }  
	
	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}
	public static Date stringToDate(String dateString) {
		Date date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateString);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 接收一个集合，返回String，String以逗号隔开
	 */
	public static String collectionToString(Collection<String> cs) {
		String str="";
		for (String s : cs) {
			str=str+s+",";
		}
		if(str.length()>1)
		return str.substring(0, str.length()-1);
		else return "";
	}
	
	
	/**
	 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
	 */
	 public static List<String> StringToList(String listText) {  
	        if (listText == null || listText.equals("")) {  return null; }  
	        List<String> list = new ArrayList<String>();  
	        String[] text = listText.split(","); 
	        for (String str : text) { list.add(str);}  
	        return list;  
	    } 
	 
	 
	 
}
