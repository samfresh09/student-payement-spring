package com.samfresh09.studentmanagebackend;

import com.samfresh09.studentmanagebackend.entities.Payment;
import com.samfresh09.studentmanagebackend.entities.Student;
import com.samfresh09.studentmanagebackend.enums.PaymentStatus;
import com.samfresh09.studentmanagebackend.enums.PaymentType;
import com.samfresh09.studentmanagebackend.services.interfaces.PaymentService;
import com.samfresh09.studentmanagebackend.services.interfaces.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StudentManageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManageBackendApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(StudentService studentService, PaymentService paymentService){
		return args->{
			Student student1=  Student.builder()
					.id(UUID.randomUUID())
					.firstName("John")
					.lastName("Doe")
					.email("john.doe@gmail.com")
					.code("112233")
					.programId("GL")
					.build();
			Student student2=  Student.builder()
					.id(UUID.randomUUID())
					.firstName("Marc")
					.lastName("Pierre")
					.email("pierre@gmail.com")
					.code("112244")
					.programId("GL")
					.build();
			Student student3=  Student.builder()
					.id(UUID.randomUUID())
					.firstName("Alex")
					.lastName("La Tour")
					.email("alex@gmail.com")
					.code("112255")
					.programId("RSS")
					.build();
			Student student4=  Student.builder()
					.id(UUID.randomUUID())
					.firstName("Samto")
					.lastName("De L'ete")
					.email("samto@gmail.com")
					.code("112266")
					.programId("WEB")
					.build();
			Student student5=  Student.builder()
					.id(UUID.randomUUID())
					.firstName("Dave")
					.lastName("Brown")
					.email("dave@gmail.com")
					.code("112277")
					.programId("IA")
					.build();
			studentService.saveSudent(student1);
			studentService.saveSudent(student2);
			studentService.saveSudent(student3);
			studentService.saveSudent(student4);
			studentService.saveSudent(student5);

			//CREATION DES PAYEMENT

			PaymentType[] types = PaymentType.values();

			studentService.getAllStudents().forEach(student -> {
				for (int i = 0; i < 10; i++) {
					int index = new Random().nextInt(types.length);
					Payment payment= Payment.builder()
							.amount(1000+ Math.random()*20000)
							.date(LocalDate.now())
							.type(types[index])
							.status(PaymentStatus.CREATED)
							.student(student)
							.build();
					paymentService.savePaymentSimple(payment);

				}
			});




		};
	}
}
