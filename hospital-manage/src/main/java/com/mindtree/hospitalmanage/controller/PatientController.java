package com.mindtree.hospitalmanage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.hospitalmanage.dto.PatientDto;
import com.mindtree.hospitalmanage.entity.Patient;
import com.mindtree.hospitalmanage.exception.service.ServiceException;
import com.mindtree.hospitalmanage.service.PatientService;

@RestController
public class PatientController {
	@Autowired
	PatientService patientService;

	@GetMapping(value = "/patient")
	public ResponseEntity<Map<String, Object>>assignPatient(@RequestParam String doctorName, @RequestParam String patientName)
			throws ServiceException {
		PatientDto result=patientService.assignPatient(doctorName, patientName);
		Map<String, Object> success = new HashMap<String, Object>();

		success.put("Status", HttpStatus.OK);
		success.put("TimeStamp", new Date());
		success.put("Body", result);
		return new ResponseEntity<Map<String,Object>>(success,HttpStatus.OK);
	}
//	@GetMapping(value = "/patients")
//	public List<Patient> getAllPatients(){
//		List<Patient>res= patientService.getAllPatients();
//		return res;
//	}
//	public String addFile(ArrayList<Patient> res) {
//		String message="";
//	try {
//		File file = new File("D:\\bhaskar\\HosipitalPatient.txt");
//		file.createNewFile();
//		FileWriter fout = new FileWriter(file);
//		fout.write(res.toString());
//		fout.close();
//		message="succesfully saved file";
//	} catch (IOException e) {
//		e.printStackTrace();
//	} 
//	return message;
//}

}
