package com.samarth.student.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samarth.student.dao.StudentDao;
import com.samarth.student.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	
	private final StudentDao studentdao;
	
	public StudentController(StudentDao studentdao) {
		this.studentdao = studentdao;
	}
	
	@GetMapping
	public List<Student> getAllStudents() {
        return studentdao.getAllStudents();
    }
	
	@GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentdao.getStudentById(id);
    }

    @PostMapping("/")
    public String addStudent(@RequestBody Student student) {
    	System.out.println(student.toString());
        studentdao.addStudent(student);
        return "Student added successfully!";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        studentdao.updateStudent(student);
        return "Student updated successfully!";
    }
    
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentdao.deleteStudent(id);
        return "Student deleted successfully!";
    }
    
}
