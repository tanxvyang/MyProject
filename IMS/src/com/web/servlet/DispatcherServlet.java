package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.factory.ObjectFactory;
import com.web.mvc.Action;
import com.web.mvc.MVCConfig;
import com.web.mvc.MVCConfigParser;
import com.web.mvc.Package;
import com.web.mvc.Result;
/**
 * �ַ���������url������ַ�����ͬ��action�����д�����ת����ҳ��
 * @author Administrator
 *
 */
public class DispatcherServlet extends HttpServlet{
	//ÿ�ε��÷�������Ҫ�½�һ��action����󣬲�����java���˼�룬ʹ��mapʵ�ֵ���
	//actionMap���ڱ���ÿһ��action���Ψһ����
	//key    ��İ�·��
	//value  ��Ķ���
	private Map<String, Object> actionMap = new HashMap<String, Object>();
	
	private MVCConfig config;
	
	@Override
	public void init() throws ServletException {
		//����javaBean����
		ObjectFactory factory = new ObjectFactory();
		//���mvc�����ö���
		config = MVCConfigParser.getConfig();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
//		String pathInfo = req.getPathInfo();
//		System.out.println(pathInfo);
		String uri = req.getRequestURI();//����Ŀ�����ڵ�ȫ·��
		String path = req.getContextPath();//��Ŀ��·��
//		String packageAndAction = uri.substring(path.length() + 1, uri.length() - 3);
//		String[] strs = packageAndAction.split("/");
//		String packageNameSpace = strs[0];
//		String actionName = strs[1];
		//ͨ���ַ����ķָ��ҵ�namespace��actionName
		String packageNameSpace = uri.substring(path.length() + 1, uri.lastIndexOf("/"));///QQQZone/user/login.do
		String actionName = uri.substring(uri.lastIndexOf("/") + 1,uri.lastIndexOf(".do"));
		//���mvc�����ö���
//		MVCConfig config = MVCConfigParser.getConfig();
		//�����ö����л��package����
		Package packageObj = config.getPackage(packageNameSpace);
		//��package�����л��action����
		Action action = packageObj.getAction(actionName);
		
		try {
			//ͨ�������ҵ�action��Ӧ��java��
//			Class clazz = Class.forName(action.getClassName());
			Class clazz = ObjectFactory.getBean(action.getId()).getClass();
			//ͨ�������ҵ�action��Ӧ�����еķ���
			Method method = clazz.getMethod(action.getMethod(), HttpServletRequest.class,HttpServletResponse.class);
			//����java��Ķ������Ե���action��Ӧ�����еķ���
			Object clazzObj = actionMap.get(action.getId());
			if(clazzObj == null) {
				clazzObj = ObjectFactory.getBean(action.getId());
				actionMap.put(action.getId(), clazzObj);
			}
			
			Object obj = method.invoke(clazzObj, req,resp);
			//ͨ�������ķ���ֵ���ҵ����
			String resultName = obj.toString();
			Result result = action.getResult(resultName);
			//���ݽ�������ת��ʽ��ת��/�ض���
			String type = result.getType();
			if(type.equals("forward")) {
				//����result�����õ�·������ת��
				req.getRequestDispatcher(result.getTargetUrl()).forward(req, resp);
			}else if(type.equals("redirect")) {
				//����result�����õ�·�������ض���
				resp.sendRedirect(path + result.getTargetUrl());
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
}
