package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.pojo.Student;
import com.ssm.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/getAll")
	public ModelAndView getAllStudent() {
		ModelAndView mv = new ModelAndView();
		List<Student> students = studentService.selectAllStudent();
		mv.addObject("students", students);
		mv.setViewName("/showStudents.jsp");
		return mv;
	}
	@RequestMapping("/modifyStudent")
	public void modifyStudent(Student stu) {
		studentService.modifyStudent(stu);
	}
}
