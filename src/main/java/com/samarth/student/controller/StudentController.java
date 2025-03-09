package com.samarth.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samarth.student.dao.StudentDao;
import com.samarth.student.exception.InvalidStudentDataException;
import com.samarth.student.exception.StudentNotFoundException;
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
        Optional<Student> student =  studentdao.getStudentById(id);
        if(student.isEmpty()) {
        	System.out.println("no student found.");
        }
        return student.orElseThrow(() -> new StudentNotFoundException("Student not Found with id: " + id));
    }

    @PostMapping("/")
    public String addStudent(@RequestBody Student student) {
    	System.out.println(student.toString());
    	if(student.getName() == null || student.getName().trim().isEmpty()) {
    		throw new InvalidStudentDataException("Name can not be empty.");
    	}
    	if(student.getAge() < 4 || student.getAge() > 18) {
    		throw new InvalidStudentDataException("Age can not be less than 4 and greater than 18.");
    	}
    	if(student.getMarks() < 0) {
    		throw new InvalidStudentDataException("Marks can not be negative.");
    	}
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
