package com.web.mvc;

import java.util.HashMap;
import java.util.Map;
/**
 * mvc�����ö���
 * @author Administrator
 *
 */
public class MVCConfig {
	//�����ļ��г��ֵ�����package����
	//String �û�������ʹ�õ������ռ�
	//Package �������ռ��Ӧ��package����
	private Map<String, Package> packages = new HashMap<String, Package>();
	/**
	 *   ���������ռ��ȡ��Ӧ��package����
	 * @param name
	 * @return
	 */
	public Package getPackage(String name) {
		return packages.get(name);
	}
	
	public Map<String, Package> getPackages() {
		return packages;
	}

	public void setPackages(Map<String, Package> packages) {
		this.packages = packages;
	}
}
