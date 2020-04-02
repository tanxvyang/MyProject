package com.web.TEST;

import java.util.Date;
import java.util.List;

import com.web.dao.DeptDao;
import com.web.dao.EmployeeDao;
import com.web.dao.HolidayDao;
import com.web.dao.impl.DeptDaoImpl;
import com.web.dao.impl.EmployeeDaoImpl;
import com.web.dao.impl.HolidayDaoImpl;
import com.web.factory.ObjectFactory;
import com.web.pojo.Dept;
import com.web.pojo.Employee;
import com.web.pojo.Holiday;
import com.web.service.DeptService;
import com.web.service.EmployeeService;
import com.web.service.HolidayService;
import com.web.service.UserService;
import com.web.service.impl.EmployeeServiceImpl;
import com.web.service.impl.HolidayServiceImpl;
import com.web.service.impl.UserServiceImpl;
import com.web.util.Pager;

public class Test {	
	public static void main(String[] args) {
			DeptService service =  (DeptService) ObjectFactory.getBean("deptService");
//		
//		try {
//			Pager<Dept> pager = service.getDeptByPage(1);
//			System.out.println(pager.getList().size());
//			System.out.println("ser"+ pager.getList().get(0));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		
//		
//		Dept dept = new Dept();
//		dept.setDeptNo("E5");
//		dept.setDeptName("财");
//		dept.setDeptLoc("306");
//		dept.setDeptManager("哈哈");
//		try {
//			service.addDept(dept);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		DeptDao deptDao = new DeptDaoImpl();
//		Dept dept = deptDao.selectDeptByName("阿萨德");
//		if(dept == null || dept.equals("")){
//			System.out.println("不存在");
//		}else{
//			System.out.println("存在");
//		}
//		deptDao.insertDept(dept);
//		List<Dept> depts = deptDao.selectAllDept();
//		for (Dept dept2 : depts) {
//			System.out.println(dept2);
//			//deptDao.deleteDept(dept2.getDeptNo());
//		}
		
		//EmployeeDao manager= new EmployeeDaoImpl();
		//manager.selectEmpByName("李雷");
		//System.out.println(manager);
		
		/*
		 //查询部门员工
		  * 
		
		EmployeeDao employeeDao = new  EmployeeDaoImpl();
	 List<Employee> employees = employeeDao.selectUserBydept("总经");
	 if (employees.size() == 0) {
		System.out.println("该部门无员工");
	}
	 for (Employee employee : employees) {
		System.out.println(employee);
	}
		 * */
		
		/**
		 * 删除功能
		
		 
		try {
			service.removeDept("A1231", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 */
//		try {
//			Dept dept = service.getDeptMessage("V45");
//			
//			System.out.println(dept);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		EmployeeService a = new EmployeeServiceImpl();
//		try {
//			a.removeEmployee(1);
//			System.out.println("删除成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("是部门负责人");
//		}
//		
//			EmployeeDao b = new EmployeeDaoImpl();
//			String name = b.getEmployeeMessage(1).getName();
//			System.out.println(name);
			
//			HolidayService a = new HolidayServiceImpl();
//			HolidayDao b = new HolidayDaoImpl();
//			String c = b.selectStatusById(2).getStatus();
//			System.out.println(c);
//			try {
//				a.removeHoliday(2);
//				System.out.println("123");
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("234");
//			}
//			EmployeeService a = new EmployeeServiceImpl();
//			try {
//				List<Employee> list = a.selectOneNo("阿萨德");
//				for (Employee employee : list) {
//					System.out.println(employee);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			UserService userService = new UserServiceImpl();
			try {
				userService.deleteUser("张泰");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
