package com.samarth.student.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.samarth.student.model.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		 return new Student(
		            rs.getInt("id"),
		            rs.getString("name"),
		            rs.getInt("marks"),
		            rs.getInt("age")
		        );
	}
	

}
