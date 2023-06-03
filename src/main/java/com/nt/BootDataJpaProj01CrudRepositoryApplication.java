package com.nt;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.entity.Doctor;
import com.nt.service.IDoctorService;

@SpringBootApplication
public class BootDataJpaProj01CrudRepositoryApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =	SpringApplication.run(BootDataJpaProj01CrudRepositoryApplication.class, args);
		
		IDoctorService service = ctx.getBean("doctorService", IDoctorService.class);


		try {
			/*Doctor doc= new Doctor();
			doc.setDocName("Ramesh");
			doc.setSpecialization("MD-Cardio");
			doc.setIncome(89000.0);
			String result=service.registerDoctor(doc);
			System.out.println(result);
			
				System.out.println("__________________________________________________");
			
			Doctor doc1= new Doctor();
			doc1.setDocName("Brevies");
			doc1.setIncome(90000.0);
			doc1.setSpecialization("Cardio");
			
				System.out.println("__________________________________________________");
				
			Doctor doc2= new Doctor();
			doc2.setDocName("Vaibhaw");
			doc2.setIncome(80000.0);
			doc2.setSpecialization("physician");
			
				System.out.println("__________________________________________________");
				
			Doctor doc3= new Doctor();
			doc3.setDocName("Rohit");
			doc3.setIncome(100000.0);
			doc3.setSpecialization("Ortho");
			
				System.out.println("__________________________________________________");
				
			String msg=service.registerGroupOfDoctors(List.of(doc1,doc2,doc3));
			 or
			String msg=service.registerGroupOfDoctors(Arrays.asList(doc1,doc2,doc3));
			System.out.println(msg); 
			*/
			System.out.println("__________________________________________________");

			System.out.println("Count of Records ::" + service.fecthDoctorsCount());

			System.out.println("__________________________________________________");

			System.out.println("11 Id doctor exists? ::" + service.checkDoctorAvailbility(11));

			System.out.println("__________________________________________________");

			Iterable<Doctor> it = service.showAllDoctors();
			it.forEach(dc -> { // Java 8 forEach(-)method
				System.out.println(dc);
			});

			System.out.println("--------------------------");
			it.forEach(dc -> System.out.println(dc));
			
			System.out.println("------------------------");
			it.forEach(System.out::println);
			
			System.out.println("--------------------------");
			for (Doctor dc : it) {
				System.out.println(dc);
			}

			System.out.println("__________________________________________________");

			service.showAllDoctorsByIds(List.of(1, 2, 10, 200, 101)).forEach(System.out::println);

			System.out.println("__________________________________________________");

			Doctor doctor = service.showDoctorById(5);
			System.out.println(doctor);

			System.out.println("_________________________________________________________________");

			System.out.println(service.updateDoctorIncomeById(5, 20.0f));
			System.out.println("5 doctor info ::"+service.showDoctorById(5));
			
			System.out.println("_________________________________________________________________");

			Doctor doc= new Doctor();
			doc.setDocId(111);
			doc.setDocName("karan");
			doc.setIncome(98000.0);
			doc.setSpecialization("Cardio");
			System.out.println(service.registerOrUpdateDoctor(doc));
			
			System.out.println("_________________________________________________________________");

			System.out.println(service.deleteDoctorById(21));
			
			System.out.println("_________________________________________________________________");

			Doctor doc1= new Doctor();
			doc1.setDocId(24);
			doc1.setDocName("karan");
			System.out.println(service.deleteDoctor(doc1));
			
			/*	System.out.println("_________________________________________________________________");
			
				System.out.println(service.removeAllDoctors());
				
				System.out.println("_________________________________________________________________");
			
				System.out.println(service.removeDoctorsByIds(List.of(14,15)));
				*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		((ConfigurableApplicationContext) ctx).close();

	}

}
