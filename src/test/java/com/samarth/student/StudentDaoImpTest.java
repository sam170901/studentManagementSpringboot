package com.samarth.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.Mockito.*;

import com.samarth.student.dao.StudentDaoImp;
import com.samarth.student.model.Student;
import com.samarth.student.rowMapper.StudentRowMapper;

@ExtendWith(MockitoExtension.class)
public class StudentDaoImpTest {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private StudentDaoImp studentDao;
	
	private Student student;
	
	@BeforeEach
	public void setUp() {
		student = new Student(1, "Samarth", 95,23);
	}
	
	@Test
	public void testGetAllStudents() {
		List<Student> students = Arrays.asList(student);
		
		when(jdbcTemplate.query(anyString(), any(StudentRowMapper.class)))
		        .thenReturn(students);

		assertEquals(1,studentDao.getAllStudents().size());
	}
	
	@Test
    void testGetStudentById() {
//        when(jdbcTemplate.queryForObject(anyString(), any(), anyInt())).thenReturn(student);
		when(jdbcTemplate.queryForObject(anyString(), any(StudentRowMapper.class), anyInt())).thenReturn(student);

        
        Student stud = studentDao.getStudentById(1);
        assertEquals("Samarth", stud.getName());
    }

    @Test
    void testAddStudent() {
        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);
        assertEquals(1, studentDao.addStudent(student));
    }

    @Test
    void testUpdateStudent() {
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);
        assertEquals(1, studentDao.updateStudent(student));
    }

    @Test
    void testDeleteStudent() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);
        assertEquals(1, studentDao.deleteStudent(1));
    }
	
	
}
