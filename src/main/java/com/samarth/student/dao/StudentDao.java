package com.samarth.student.dao;

import java.util.List;

import com.samarth.student.model.Student;

public interface StudentDao {
	List<Student> getAllStudents();
	Student getStudentById(int id);
    int addStudent(Student student);
    int updateStudent(Student student);
    int deleteStudent(int id);
}
