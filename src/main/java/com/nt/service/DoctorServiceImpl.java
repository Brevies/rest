package com.nt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.IDoctorRepo;
import com.nt.entity.Doctor;

@Service("doctorService")
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private IDoctorRepo repo;

	@Override
	public String registerDoctor(Doctor doctor) {
		System.out.println("Repo proxy class is ::" + repo.getClass());
		System.out.println("Doc id(before save::" + doctor.getDocId());
		Doctor doc = repo.save(doctor);
		return "Doctor obj is saved with id value :" + doc.getDocId();
	}

	/*
		@Override
		public String registerGroupOfDoctors(Iterable<Doctor> doctors) {
			if (doctors != null) {
				Iterable<Doctor> savedDoctors = repo.saveAll(doctors);
				int size = ((Collection) savedDoctors).size();
				List<Integer> lds = (List<Integer>) ((Collection) savedDoctors).stream().map(d -> ((Doctor) d).getDocId())
						.collect(Collectors.toList());
				return size + "no.of doctors are saved with id Values" + lds.toString();
			} else
				throw new IllegalArgumentException("Invalid doctors info");
		}                         OR                    	*/

	@Override
	public String registerGroupOfDoctors(Iterable<Doctor> doctors) {
		if (doctors != null) {
			Iterable<Doctor> savedDoctors = repo.saveAll(doctors);
			int size = ((Collection) savedDoctors).size();
			List<Integer> lds = new ArrayList();
			savedDoctors.forEach(d -> {
				lds.add(d.getDocId());
			});
			return size + "no.of doctors are saved with id Values" + lds.toString();
		} else
			throw new IllegalArgumentException("Invalid doctors info");

	}

	@Override
	public long fecthDoctorsCount() {
		return repo.count();
	}

	@Override
	public boolean checkDoctorAvailbility(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Iterable<Doctor> showAllDoctors() {
		return repo.findAll();
	}

	@Override
	public Iterable<Doctor> showAllDoctorsByIds(Iterable<Integer> ids) {
		return repo.findAllById(ids);
	}

	/*@Override
	public Doctor showDoctorById(Integer id) {
		Doctor dutyDoctor= new Doctor();
		dutyDoctor.setSpecialization("duty doctor");
		Doctor doctor=repo.findById(id).orElse(dutyDoctor);
		return doctor;
	}    // OR    */

	/*@Override
	public Doctor showDoctorById(Integer id) {
		Doctor doctor=repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Doctor Id"));
		return doctor;
	}    // OR     */

	@Override
	public Doctor showDoctorById(Integer id) {
		Optional<Doctor> op = repo.findById(id);
		if (op.isPresent())
			return op.get();
		else
			throw new IllegalArgumentException("Invalid Doctor id");
	}

	@Override
	public String updateDoctorIncomeById(Integer id, float hikePercentage) {

		// Load Object
		Optional<Doctor> opt = repo.findById(id);
		if (opt.isPresent()) {
			Doctor doctor = opt.get();
			double newIncome = doctor.getIncome() + (doctor.getIncome() * (hikePercentage / 100.0));

			// set new income to entity object
			doctor.setIncome(newIncome);

			// partially update the object
			return repo.save(doctor).getDocId() + " is Updated with " + newIncome;
		} else {
			return id + " Doctor not Found";
		}
	}

	/*@Override
	public Optional<Doctor> updateDoctorIncomeById(Integer id, float hikePercentage) {
		Optional<Doctor> doc= repo.findById(id);
		return doc;
	}*/

	@Override
	public String registerOrUpdateDoctor(Doctor doctor) {
		// load Doctor object
		Optional<Doctor> opt = repo.findById(doctor.getDocId());
		if (opt.isPresent()) {
			repo.save(doctor); // For update obj operation
			return doctor.getDocId() + " Doctor details are found and updated";
		} else {
			return "Doctor is saved with id value:" + repo.save(doctor).getDocId();
		}
	}

	@Override
	public String deleteDoctorById(Integer id) {
		// load Object
		Optional<Doctor> opt = repo.findById(id);
		if (opt.isPresent()) {
			repo.deleteById(id);
			return id + " doctor is deleted ";
		} else {
			return id + " doctor not found for deletion";
		}
	}

	@Override
	public String deleteDoctor(Doctor doctor) {
		// Load Object
		Optional<Doctor> opt=repo.findById(doctor.getDocId());
		if(opt.isEmpty()) {
			return doctor.getDocId()+"Doctor is not Found";
		}
		else {
			repo.delete(opt.get());
			return doctor.getDocId()+" doctor found and deleted";
		}
	}

	@Override
	public String removeAllDoctors() {
		long count=repo.count();
		if(count>0) {
			repo.deleteAll();
			return count+" no of reocrds are deleted";
		} else {
			return "no of rocords found delete";
		}
		
	}

	@Override
	public String removeDoctorsByIds(List<Integer> ids) {
		List <Doctor> docList=(List<Doctor>)repo.findAllById(ids);
		if(docList.size()==ids.size()) {
			repo.deleteAllById(ids);
			return docList.size()+" no of records are deleted";
		} 
		return " Some of given id value records are not found in the db table";
	}

	
}