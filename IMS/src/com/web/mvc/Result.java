package com.web.mvc;
//	<result name="success">/qqqZoneIndex.jsp</result>
public class Result {
	//actionͨ������name����������ִ�����Ҫ��ת�ĸ�result�����õĵ�ַ
	private String name;
	//���������ַ�ʽ���ض����ת��������ͨ��type�����Զ���
	private String type;
	//��ǰresult�����õ�Ŀ���ַ
	private String targetUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
}
