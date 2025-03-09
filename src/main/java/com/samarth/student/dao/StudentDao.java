package com.samarth.student.dao;

import java.util.List;
import java.util.Optional;

import com.samarth.student.model.Student;

public interface StudentDao {
	List<Student> getAllStudents();
	Optional<Student> getStudentById(int id);
    int addStudent(Student student);
    int updateStudent(Student student);
    int deleteStudent(int id);
}
