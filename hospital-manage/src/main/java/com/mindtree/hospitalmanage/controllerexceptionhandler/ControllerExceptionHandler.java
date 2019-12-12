package com.mindtree.hospitalmanage.controllerexceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.hospitalmanage.controller.DoctorController;
import com.mindtree.hospitalmanage.controller.PatientController;
import com.mindtree.hospitalmanage.dto.ExceptionDto;
import com.mindtree.hospitalmanage.exception.ControllerException;
import com.mindtree.hospitalmanage.exception.service.ServiceException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@Value("${spring.application.name}")
	private String appliactionName;
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Map<String, Object>> exceptioHandler(ControllerException c)
	{
		Map<String, Object> error = new HashMap<String, Object>();		
		error.put("status",HttpStatus.NOT_FOUND.value());
		error.put("ErrorMessage",c.getMessage());	 
		error.put("TimeStamp",new Date());
		error.put("Application_Name",appliactionName);
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.NOT_FOUND);
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Map<String, Object>> exceptioHandler(Exception c)
	{
		Map<String, Object> error = new HashMap<String, Object>();		
		error.put("status",HttpStatus.NOT_FOUND.value());
		error.put("ErrorMessage",c.getMessage());	 
		error.put("TimeStamp",new Date());
		error.put("Application_Name",appliactionName);
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.NOT_FOUND);
		
	}
}
