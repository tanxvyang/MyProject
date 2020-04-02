package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.StudentDao;
import com.ssm.pojo.Student;
import com.ssm.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao studentDao;
	public List<Student> selectAllStudent() {
		return studentDao.selectAll();
	}
	@Transactional
	public void modifyStudent(Student stu) {
		studentDao.updateStudent(stu);
	}

}
