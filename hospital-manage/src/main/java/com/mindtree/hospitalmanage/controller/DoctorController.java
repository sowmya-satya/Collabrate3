package com.mindtree.hospitalmanage.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.hospitalmanage.dto.DoctorDto;
import com.mindtree.hospitalmanage.exception.ControllerException;
import com.mindtree.hospitalmanage.exception.service.ServiceException;
import com.mindtree.hospitalmanage.service.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	@GetMapping(value = "/doctor")
	public ResponseEntity<Map<String, Object>> getAllDoctors() throws ServiceException {
		List<DoctorDto> result = doctorService.getAllDoctors();
		Map<String, Object> success = new HashMap<String, Object>();

		success.put("Status", HttpStatus.OK);
		success.put("TimeStamp", new Date());
		success.put("Body", result);
		return new ResponseEntity<Map<String, Object>>(success, HttpStatus.OK);
	}

	@GetMapping(value = "/doctors")
	public ResponseEntity<Map<String, Object>> displayDoctors(@RequestParam int pc) throws ServiceException {
		List<DoctorDto> result=doctorService.displayDoctors(pc);
		Map<String, Object> success = new HashMap<String, Object>();

		success.put("Status", HttpStatus.OK);
		success.put("TimeStamp", new Date());
		success.put("Body", result);
		return new ResponseEntity<Map<String,Object>>(success,HttpStatus.OK);
	}
	@GetMapping("/getdoctors")
	public ResponseEntity<Map<String, Object>> getDoctors() throws ControllerException, ServiceException {
		List<DoctorDto> result = new ArrayList<DoctorDto>();
		result = doctorService.getAllDoctors();
		File file=new File("D://bhaskar//hospital.txt");
		try {
			FileWriter fileWriter=new FileWriter(file);
			for (DoctorDto doctorDto : result) 
			{
				fileWriter.write(""+doctorDto);
				fileWriter.write("\n");
			}
			fileWriter.close();
		}
		catch (IOException e) {
			// TODO: handle exception
			throw new ControllerException("file not found");
		}
		Map<String, Object> success = new HashMap<String, Object>();

		success.put("Status", HttpStatus.OK);
		success.put("TimeStamp", new Date());
		success.put("Body", result);
		return new ResponseEntity<Map<String,Object>>(success,HttpStatus.OK);
	}
}
