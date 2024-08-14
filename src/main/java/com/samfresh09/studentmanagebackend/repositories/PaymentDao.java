package com.samfresh09.studentmanagebackend.repositories;

import com.samfresh09.studentmanagebackend.entities.Payment;
import com.samfresh09.studentmanagebackend.enums.PaymentStatus;
import com.samfresh09.studentmanagebackend.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentDao extends JpaRepository<Payment, UUID> {
    Payment findByStudentCode(String code);
    List<Payment> findByType(PaymentType type);
    List<Payment> findByStatus(PaymentStatus status);
}
