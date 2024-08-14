package com.samfresh09.studentmanagebackend.services.impl;

import com.samfresh09.studentmanagebackend.entities.Payment;
import com.samfresh09.studentmanagebackend.entities.Student;
import com.samfresh09.studentmanagebackend.enums.PaymentStatus;
import com.samfresh09.studentmanagebackend.enums.PaymentType;
import com.samfresh09.studentmanagebackend.repositories.PaymentDao;
import com.samfresh09.studentmanagebackend.services.interfaces.PaymentService;
import com.samfresh09.studentmanagebackend.services.interfaces.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private PaymentDao paymentDao;
    private StudentService studentService;

    @Override
    public Payment savePayment(MultipartFile file, double amount,PaymentType type,String code) throws IOException {
        System.out.println(file.getInputStream());
        try {
            //Specification du repectoire qui vas stocker nos pdf
            Path folderPath= Paths.get(System.getProperty("user.home"),"spring","pdf");

            //si le repectoire n'existe pas le creer
            if (!Files.exists(folderPath)){
                Files.createDirectories(folderPath);
            }
            // definir le nom du ficher a stocker
            String fileName = UUID.randomUUID().toString();

            //le chemin specifiant l'emplacement du ficher
            Path filePath= Paths.get(System.getProperty("user.home"),"spring","pdf",fileName+".pdf");

            //copy du ficher specifer dans notre repectoire
            Files.copy(file.getInputStream(),filePath);

            Student student= this.studentService.findStudentByCode(code);
            //moifification du nom du ficher dans le payement
            Payment payment= Payment.builder()
                    .amount(amount)
                    .date(LocalDate.now())
                    .status(PaymentStatus.CREATED)
                    .file(filePath.toUri().toString())
                    .student(student)
                    .build();
            return this.paymentDao.save(payment);
        }catch (Exception e){
            throw new RuntimeException("Error saving payment");
        }
    }

    @Override
    public Payment savePaymentSimple( Payment payment) {
        try {
            return this.paymentDao.save(payment);
        }catch (Exception e){
            throw new RuntimeException("Error saving payment");
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return this.paymentDao.findAll();
    }

    @Override
    public Payment getPaymentById(UUID id) {
        return this.paymentDao.findById(id).orElse(null);
    }

    @Override
    public boolean deletePaymentById(UUID id) {
        boolean result=false;
        if (this.paymentDao.existsById(id)) {
            this.paymentDao.deleteById(id);
            result=true;
        }
        return result;
    }

    @Override
    public Payment updatePayment(UUID id, Payment payment) {
        if (!(this.paymentDao.existsById(id) && payment.getId().equals(id)) ) {
            throw new RuntimeException("Error Client: don't match data");
        }
        return this.paymentDao.save(payment);
    }

    @Override
    public List<Payment> getPaymentWithStudent(String code) {
        if (!this.studentService.getStudentExist(code)){
            throw new RuntimeException("Error Client: don't match data");
        }
        return this.paymentDao.findByStudentCode(code);
    }

    @Override
    public List<Payment> getPaymentWithType(PaymentType type) {
        return this.paymentDao.findByType(type);
    }

    @Override
    public List<Payment> getPaymentWithStatus(PaymentStatus status) {
        return this.paymentDao.findByStatus(status);
    }
}
