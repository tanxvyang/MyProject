package com.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.dao.StudentDao;
import com.ssm.pojo.Student;
@Repository
public class StudentDaoImpl implements StudentDao{

	private StudentDao studentDao;
	
	public List<Student> selectAll() {
		return studentDao.selectAll();
	}
	public void updateStudent(Student stu) {
		studentDao.updateStudent(stu);
	}
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.studentDao = factory.openSession().getMapper(StudentDao.class);
	}

}
