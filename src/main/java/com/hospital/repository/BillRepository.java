package com.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.Bill;
import com.hospital.entity.PaymentStatus;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByPaymentStatus(PaymentStatus paymentStatus);
    
}