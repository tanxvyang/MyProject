package com.ssm.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	public static void main(String[] args) {
//		String fileName = "1.png";
//		String suffix = fileName.substring(fileName.indexOf('.'), fileName.length());
//		System.out.println(suffix);
//		System.out.println(fileName.indexOf('.'));
//		System.out.println(fileName.length());
//		Map<Integer,String> proMap = new HashMap<Integer,String>();
//		proMap.put(1, "程序员");
//		proMap.put(2, "DBA");
//		proMap.put(3, "测试");
//		proMap.put(4, "实施");
//		proMap.put(5, "销售");
//		for (Entry entry : proMap.entrySet()) {
//		}
		File f = new File("~");
		System.out.println(f.exists());
	}
}
