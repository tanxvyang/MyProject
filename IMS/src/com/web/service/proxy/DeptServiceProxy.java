package com.web.service.proxy;

import java.util.List;

import com.web.pojo.Dept;
import com.web.pojo.User;
import com.web.service.DeptService;
import com.web.trans.Transaction;
import com.web.util.Pager;

public class DeptServiceProxy implements DeptService{
	private DeptService deptService;
	private Transaction trans;
	
	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	
	
	/**
	 * 12-11 9:12完成了部门添加
	 */
	public void addDept(Dept dept) throws Exception {
		
		trans.begin();
		try {
			deptService.addDept(dept);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
	}

	public List<Dept> selectAllDept() throws Exception {
		trans.begin();
		List<Dept> list;
		try {
			list = deptService.selectAllDept();
			System.out.println("123"+list);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return list;
	}

/**
 * 12-12 10:02
 * tan
 * @throws Exception 
 */
	public void updateDept(Dept dept) throws Exception {
		trans.begin();
		try {
			deptService.updateDept(dept);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}

	public Pager<Dept> getDeptByPage(Integer pageNo) throws Exception {
		trans.begin();
		Pager<Dept> pager;
		try {
			pager = deptService.getDeptByPage(pageNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return pager;
		
	}

	public void removeDept(String deptNo) throws Exception {
		trans.begin();
		try {
			deptService.removeDept(deptNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}	
		
	}

	public Dept getDeptMessage(String deptNo) throws Exception {
		trans.begin();
		Dept dept ;
		try {
			 dept = deptService.getDeptMessage(deptNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return dept;
	}

	public Dept selectDeptNameByDeptNo(String deptNo) throws Exception {
		trans.begin();
		Dept dept ;
		try {
			 dept = deptService.selectDeptNameByDeptNo(deptNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return dept;
	}

}