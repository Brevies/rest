package com.nt.dao;

import org.springframework.data.repository.CrudRepository;

import com.nt.entity.Doctor;

public interface IDoctorRepo extends CrudRepository<Doctor, Integer> {
 System.out.println("Hello");
}
