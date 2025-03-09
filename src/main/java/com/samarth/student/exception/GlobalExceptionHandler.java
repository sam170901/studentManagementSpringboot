package com.samarth.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(StudentNotFoundException.class)
	    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex){
	    	return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(InvalidStudentDataException.class)
	    public ResponseEntity<String> handleInvalidStudentDataException(InvalidStudentDataException ex){
	    	return new ResponseEntity<>(ex.fillInStackTrace().getMessage(), HttpStatus.BAD_REQUEST);
	    }
}
