package com.web.mvc;
//<action name="login" class="com.web.action.UserAction" method="doLogin">
//	<result name="success">/qqqZoneIndex.jsp</result>
//	<result name="fail">/qqqZoneLogin.jsp</result>
//</action>

import java.util.HashMap;
import java.util.Map;

public class Action {
	//����Ҫ���ǽ����������ռ������ڵ�ַ�еĲ��֣�ֱ�Ӳ�������ӵķ���
	private String name;
	//��ǰ����Ӧ�ý����ĸ���������
//	private String className;
	private String id;
	//��ǰ����Ӧ�ý������е��ĸ�����������
	private String method;
	//����������Ϻ���Ҫ��ת���ĸ�ҳ��
	//String �����ļ���result��name���ԣ���Ŀ���ַһһ��Ӧ
	//Result ��name��Ӧ��result����
	private Map<String, Result> results = new HashMap<String, Result>();

	public Result getResult(String name) {
		return results.get(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getClassName() {
//		return className;
//	}
//
//	public void setClassName(String className) {
//		this.className = className;
//	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, Result> getResults() {
		return results;
	}

	public void setResults(Map<String, Result> results) {
		this.results = results;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
