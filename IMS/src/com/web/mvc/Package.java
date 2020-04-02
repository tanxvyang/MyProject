package com.web.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * ʹ��package��һ��action���ְ����ϣ�
 * ʹ����ͬ�������ռ����û����ʵ�ʱ�����������
 * @author Administrator
 *
 */
public class Package {
	//����Ҫ����Ҫ���������ֲ�ͬ��package��
	private String name;
	//�����ռ䣬Ҳ�����û����ʵ�����������Ŀ����ʵ�ʲ�����֮��Ĳ��� localhost:8080/��Ŀ��/�����ռ�/������
	private String namespace;
	//�ڴ˰���ӵ�е�����action
	private Map<String, Action> actions = new HashMap<String, Action>();
	//����action�����ֻ�ȡ��ǰpackage�д��ڵ�action����
	public Action getAction(String name) {
		return actions.get(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Map<String, Action> getActions() {
		return actions;
	}

	public void setActions(Map<String, Action> actions) {
		this.actions = actions;
	}
	
	
}
