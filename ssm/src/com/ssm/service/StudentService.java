package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Student;

public interface StudentService {

	public List<Student> selectAllStudent();

	public void modifyStudent(Student stu);

}
