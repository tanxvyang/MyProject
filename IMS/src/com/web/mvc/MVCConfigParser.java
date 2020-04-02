package com.web.mvc;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * �����ļ��Ľ�����
 * @author Administrator
 *
 */
public class MVCConfigParser {
	//���ļ��л�ȡ���ݣ�ת����MVCConfig���󲢷��ظ�������
	
	private static MVCConfig mvcConfig = new MVCConfig();
	
	static {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(MVCConfigParser.class.getClassLoader().getResourceAsStream("mvc.xml"));
			parse(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static MVCConfig getConfig() {
		return mvcConfig;
	}
	
	private static void parse(Document document) {
		//��ȡ��Ԫ�أ�mvc��
		Element rootElement = document.getRootElement();
		//��ȡ���е�packageԪ��
		List<Element> packageElements = rootElement.elements("package");
		//�����������е�package����
		for (Element packageElement : packageElements) {
			//��ȡ��������ֵ
			String namespace = packageElement.attributeValue("namespace");
			String packageName = packageElement.attributeValue("name");
			//����package���󣬲������Ը�ֵ
			Package packageObj = new Package();
			packageObj.setName(packageName);
			packageObj.setNamespace(namespace);
			//��ȡ��ǰpackageԪ�������е�actionԪ��
			List<Element> actionElements = packageElement.elements("action");
			//�����������е�action����
			for (Element actionElement : actionElements) {
				//��ȡ��������ֵ
				String actionName = actionElement.attributeValue("name");
//				String className = actionElement.attributeValue("class");
				String id = actionElement.attributeValue("id");
				String method = actionElement.attributeValue("method");
				//����action���󣬲������Ը�ֵ
				Action action = new Action();
				action.setId(id);
				action.setName(actionName);
//				action.setClassName(className);
				action.setMethod(method);
				//��ȡ��ǰactionԪ�������е�resultԪ��
				List<Element> resultElements = actionElement.elements("result");
				//�����������е�result����
				for (Element resultElement : resultElements) {
					//��ȡ��������
					String resultName = resultElement.attributeValue("name");
					String type = resultElement.attributeValue("type");
					String targetUrl = resultElement.getText();
					//����result���󣬲������Ը�ֵ
					Result result = new Result();
					result.setName(resultName);
					result.setType(type);
					result.setTargetUrl(targetUrl);
					//���Ӧ��action��װ��result����
					action.getResults().put(resultName, result);
				}
				//���Ӧ��package��װ��action����
				packageObj.getActions().put(actionName, action);
			}
			//װ��package����
			mvcConfig.getPackages().put(namespace, packageObj);
		}
	}
}
