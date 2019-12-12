package com.mindtree.hospitalmanage.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.hospitalmanage.dto.DoctorDto;
import com.mindtree.hospitalmanage.dto.PatientDto;
import com.mindtree.hospitalmanage.entity.Doctor;
import com.mindtree.hospitalmanage.entity.Patient;
import com.mindtree.hospitalmanage.exception.service.ServiceException;
import com.mindtree.hospitalmanage.exceptionsutil.ErrorConstants;
import com.mindtree.hospitalmanage.repository.DoctorRepository;
import com.mindtree.hospitalmanage.repository.PatientRepository;
import com.mindtree.hospitalmanage.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<DoctorDto> getAllDoctors() throws ServiceException {
		List<Doctor> doctors = doctorRepository.findAll();
		List<DoctorDto> doctorDtos = new ArrayList<DoctorDto>();
		Collections.sort(doctors, Doctor.expCompare);
		for (Doctor d : doctors) {
			List<PatientDto> patientDtos = new ArrayList<PatientDto>();
			for (Patient p : d.getPatients()) {
				PatientDto patientDto = new PatientDto();
				BeanUtils.copyProperties(p, patientDto);
				patientDtos.add(patientDto);
			}
			DoctorDto doctorDto = new DoctorDto();
			doctorDto.setPatientDtos(patientDtos);
			BeanUtils.copyProperties(d, doctorDto);
			doctorDtos.add(doctorDto);
		}

		return doctorDtos;
	}

	@Override
	public List<DoctorDto> displayDoctors(int pc) throws ServiceException {
		List<Doctor> doctors = doctorRepository.findAll();
		List<Doctor> newDoctors = new ArrayList<Doctor>();
		List<DoctorDto>doctorDtos=new ArrayList<DoctorDto>();
		for(Doctor d: doctors) {
			if(d.getPatients().size()>pc) {
				newDoctors.add(d);
			}
		}
		for(Doctor d: newDoctors) {
			List<PatientDto>patientDtos=new ArrayList<PatientDto>();
			for (Patient p : d.getPatients()) {
				PatientDto patientDto=new PatientDto();
				BeanUtils.copyProperties(p, patientDto);
				patientDtos.add(patientDto);
			}
			DoctorDto doctorDto=new DoctorDto();
			doctorDto.setPatientDtos(patientDtos);
			BeanUtils.copyProperties(d, doctorDto);
			doctorDtos.add(doctorDto);
		}
		if(doctorDtos.isEmpty()) {
			throw new ServiceException(ErrorConstants.NOPATIENTFOUND);
		}
		return doctorDtos;
//		if (pc == 0) {
//			throw new ServiceException(ErrorConstants.NOPATIENTFOUND);
//
//		} else {
//
//			newDoctors = doctors.stream().filter(e -> e.getPatients().size() > pc).collect(Collectors.toList());
//			return newDoctors.stream().map(e -> convertEntityToDto(e)).collect(Collectors.toList());
//		}
	}

	private DoctorDto convertEntityToDto(Doctor e) {
		return modelMapper.map(e, DoctorDto.class);
	}
}
