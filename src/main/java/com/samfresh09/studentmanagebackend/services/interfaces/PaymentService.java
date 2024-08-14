package com.samfresh09.studentmanagebackend.services.interfaces;

import com.samfresh09.studentmanagebackend.entities.Payment;
import com.samfresh09.studentmanagebackend.enums.PaymentStatus;
import com.samfresh09.studentmanagebackend.enums.PaymentType;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    Payment savePayment(Payment payment);
    List<Payment> getAllPayments();
    Payment getPaymentById(UUID id);
    boolean deletePaymentById(UUID id);
    Payment updatePayment(UUID id, Payment payment);
    List<Payment> getPaymentWithStudent(String code);
    List<Payment> getPaymentWithType(PaymentType type);
    List<Payment> getPaymentWithStatus(PaymentStatus status);
}
