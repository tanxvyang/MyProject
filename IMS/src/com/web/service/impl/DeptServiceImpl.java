package com.web.service.impl;

import java.util.List;

import com.web.dao.DeptDao;
import com.web.dao.EmployeeDao;
import com.web.pojo.Dept;
import com.web.pojo.Employee;
import com.web.service.DeptService;
import com.web.util.Constants;
import com.web.util.Pager;

public class DeptServiceImpl implements DeptService {
	private DeptDao deptDao;
	private EmployeeDao employeeDao;

	//jin
	public List<Dept> selectAllDept() {
		List<Dept> list = deptDao.selectAllDept();
		return list;
	}

	/**12-12 00-00
	 * tan
	 * @author soft01
	 * @throws Exception 
	 */
	
	public void updateDept(Dept dept) throws Exception {
		String manager = dept.getDeptManager();
		try {
			//List<Dept> depts = deptDao.selectDeptByDepyNoName(dept);
			Dept deptDateByNoName = deptDao.selectDeptBydeptNoAndName(dept);
			Dept deptDatebyNo = deptDao.selectDeptBydeptNo(dept.getDeptNo());
			
			Dept deptDatebyNa= deptDao.selectDeptBydeptName(dept.getDeptName());
			
			if (manager != null ) {
				//查找该员工是否存在
				Employee employees =  employeeDao.selectEmpByName(manager);
			//部门经理不存在
			 if( !(employees!=null)){
					throw new Exception(" 修改失败：部门经理不存在");
			 }else{ 
			 if(deptDateByNoName != null) {
				 //部门经理不为空， 部门名未修改，直接update
					deptDao.updateDept(dept);

				//部门经理不为空， 部门名已修改    查询为 null
			}else if(employees!=null && !(deptDateByNoName != null)) {
				//如果根据deptName可以查到部门，说明该部门名已被使用
				if(deptDatebyNa != null){
					throw new Exception(" 修改失败：该部门名已存在，请更改");
				}else{
					//否则进行更改
					deptDao.updateDept(dept);
				}
			}
			 } 
			 //未设置部门经理
			}else{
				 if(deptDateByNoName != null) {
					 // 部门名未修改，直接update
						deptDao.updateDept(dept);
				}else{
					//如果根据deptName可以查到部门，说明该部门名已被使用
					if(deptDatebyNa != null){
						throw new Exception(" 修改失败：该部门名已存在，请更改");
					}else{
						//否则进行更改
						deptDao.updateDept(dept);
					}
					
					
				}
			}
				
				
				
				
			if (deptDateByNoName != null) {
				
			}
			
			
			
			
//			if ( deptDatebyNo != null && !(deptDatebyNa != null )) {
//				if (manager == null || !manager.equals("") ) {
//						/*
//						 * 如果不为空，查找该员工是否存在（）；
//						 * */
//					if (employees != null) {
//						deptDao.updateDept(dept);
//					}else{
//						throw new Exception(" 修改失败：部门经理不存在");
//					}	
//				}else{
//					//没有经理则直接添加
//					deptDao.updateDept(dept);
//				}
//			} else {
//				
//				if (deptDatebyNo!= null && deptDatebyNa != null ) {
//					throw new Exception(" 修改失败：该部门名已存在，请更改");
//				}else if(deptDatebyNo == null ){					
//					throw new Exception(" 修改失败：该部门已被删除");
//				}
//			}
			
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			
		}
		
		
	}
	/**
	 * @author tan
	 * 12-11 14:49
	 * 删除部门
	 * @throws Exception 
	 */
	public void removeDept(String deptNo) throws Exception {
		try {
			Dept dept = deptDao.selectDeptBydeptNo(deptNo);
			if (dept!= null) {
				
				List<Employee> employees = employeeDao.selectUserBydept(dept.getDeptName());
				if (employees.size() == 0) {
					deptDao.deleteDept(deptNo);
				}else{
					throw new Exception("无法删除该部门，部门下存在员工");
				}
			}else{
				throw new Exception("该部门已删除或不存在");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	
	
	/*
	 * 12-11 9:12完成了部门添加，
	 * 遇到错误，使用  employeeDao.selectEmpByName(manager); 时报 空指针异常，
	 * 
	 * 原因 是 在bean.xml中没有配置相应的<property>
	 * 
	 * get ： 在 <bean> 中所有引用到的  <property> 都要配置相应的 xml文件
	 * 
	 * 例如：<bean id="deptService" class="com.web.service.impl.DeptServiceImpl">
			<property id="deptDao" ref="deptDao"/>
			<property id="employeeDao" ref="employeeDao"/>
			</bean>
	 * 
	 */
	
	
	public void addDept(Dept dept) throws Exception {
		/*
		 * 查找部门经理是否存在
		 * */
	
		//判断部门名和部门号是否已存在
		String manager = dept.getDeptManager();
		try {
			List<Dept> depts = deptDao.selectDeptByDepyNoName(dept);
			if (depts.size() == 0) {
				if (manager != null && !manager.equals("") ) {
						/*
						 * 如果不为空，查找该员工是否存在（）；
						 * */
					Employee employees =  employeeDao.selectEmpByName(manager);
					
					if (employees != null) {
						deptDao.insertDept(dept);
					}else{
						throw new Exception(" 添加失败：部门经理不存在");
					}	
				}else{
					throw new Exception(" 添加失败：请输入部门经理");
					//没有经理则直接添加
					//deptDao.insertDept(dept);
				}
			} else {
				throw new Exception(" 添加失败：部门号或部门名已存在");
			}
			
		} catch (Exception e) {
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("添加失败！"+e.getMessage());
		}
		
		
		
	}
/**
 * 
 * @author tan 部门详情
 * 12-11  11：20
 */
	public Dept getDeptMessage(String deptNo) throws Exception {
		Dept dept = deptDao.selectDeptBydeptNo(deptNo);
		if (dept == null || dept.equals("")) {
			throw new Exception(" 该部门不存在或已被删除！ ");
		}else{
		Integer empNumber = employeeDao.countUser(dept.getDeptName());
		dept.setDeptEmpNumber(empNumber);
		}
		return dept;
	}
	
	/**
	 * @author tanxvyang
	 * 12-10 完成了 部门分页 
	 */
	 
	public Pager<Dept> getDeptByPage(Integer pageNo) {
		Pager<Dept> pager = new Pager<Dept>();
		pager.setPageNo(pageNo);
		Integer totalCount = deptDao.countDept();
		if(totalCount == null) {
			totalCount = 0;
		}
		//
		System.out.println("totalCount = "+totalCount);
		//
		pager.setTotalPage(totalCount,  Constants.PAGE_SIZE_4);
		List<Dept> depts = deptDao.selectDeptByPage(pageNo,Constants.PAGE_SIZE_4);
		pager.setList(depts);
		return pager;
	}
	
	public Dept selectDeptNameByDeptNo(String deptNo) throws Exception {
		Dept dept = deptDao.selectDeptBydeptNo(deptNo);
		return dept;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	
	public DeptDao getDeptDao() {
		return deptDao;
	}
	
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}







	
}
