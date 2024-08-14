package com.samfresh09.studentmanagebackend.controllers;

import com.samfresh09.studentmanagebackend.entities.Payment;
import com.samfresh09.studentmanagebackend.enums.PaymentStatus;
import com.samfresh09.studentmanagebackend.enums.PaymentType;
import com.samfresh09.studentmanagebackend.services.interfaces.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping(path = "/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, double amount,PaymentType type,String code) throws IOException {
        return  this.paymentService.savePayment(file,amount,type,code);
    }

    @GetMapping("/")
    public List<Payment> getAllPayments() {
        return this.paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable UUID id) {
        return this.paymentService.getPaymentById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deletePaymentById(@PathVariable UUID id) {
        return this.paymentService.deletePaymentById(id);
    }

    @PutMapping("/")
    public Payment updatePayment(@PathVariable UUID id, @RequestBody Payment payment) {
        return this.paymentService.updatePayment(id, payment);
    }

    @GetMapping("/student/{code}")
    public List<Payment> getPaymentWithStudent(@PathVariable String code) {
        return this.paymentService.getPaymentWithStudent(code);
    }

    @GetMapping("/type/{type}")
    public List<Payment> getPaymentWithType(@PathVariable PaymentType type) {
        return this.paymentService.getPaymentWithType(type);
    }

    @GetMapping("/status/{status}")
    public List<Payment> getPaymentWithStatus(@PathVariable PaymentStatus status) {
        return this.paymentService.getPaymentWithStatus(status);
    }
}
