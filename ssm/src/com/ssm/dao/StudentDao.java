package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Student;
public interface StudentDao {
	public List<Student> selectAll();

	public void updateStudent(Student stu);
}
