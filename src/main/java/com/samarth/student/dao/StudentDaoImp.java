package com.samarth.student.dao;

import java.util.List;

import com.samarth.student.rowMapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samarth.student.model.Student;


@Repository
public class StudentDaoImp implements StudentDao {
	
	private final JdbcTemplate jdbctemplate;
	
	public StudentDaoImp(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	@Override
	public List<Student> getAllStudents() {
		String sql = "Select * from student";
		return jdbctemplate.query(sql,new StudentRowMapper());
	}

	@Override
	public Student getStudentById(int id) {
		String sql  = "Select * from student where id = ?";
		//QueryForObject used because query returns a list and we want only 1 object here. what other methods are there?
		return jdbctemplate.queryForObject(sql, new StudentRowMapper(), id);
	}

	@Override
	public int addStudent(Student student) {
		String sql = "Insert into student(name, marks, age) values(?,?,?)";
		return jdbctemplate.update(sql, student.getName(), student.getMarks(), student.getAge());
	}

	@Override
	public int updateStudent(Student student) {
		//Try these with prepared statement and named Parameters
		String sql = "UPDATE student SET name = ?, marks = ?, age = ? WHERE id = ?";
		return jdbctemplate.update(sql, student.getName(), student.getMarks(), student.getAge(), student.getId());
	}

	@Override
	public int deleteStudent(int id) {
		String sql = "Delete from student where id = ?";
		return jdbctemplate.update(sql, id);
	}
	

}
