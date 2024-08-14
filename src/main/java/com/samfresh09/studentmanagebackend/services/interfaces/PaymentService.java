package com.samfresh09.studentmanagebackend.services.interfaces;

import com.samfresh09.studentmanagebackend.entities.Payment;
import com.samfresh09.studentmanagebackend.enums.PaymentStatus;
import com.samfresh09.studentmanagebackend.enums.PaymentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PaymentService {
    Payment savePayment(MultipartFile file, double amount,PaymentType type,String code) throws IOException;
    Payment savePaymentSimple( Payment payment);
    List<Payment> getAllPayments();
    Payment getPaymentById(UUID id);
    boolean deletePaymentById(UUID id);
    Payment updatePayment(UUID id, Payment payment);
    List<Payment> getPaymentWithStudent(String code);
    List<Payment> getPaymentWithType(PaymentType type);
    List<Payment> getPaymentWithStatus(PaymentStatus status);
}
