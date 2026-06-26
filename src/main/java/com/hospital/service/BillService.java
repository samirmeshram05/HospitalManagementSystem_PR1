package com.hospital.service;

import java.util.List;

import com.hospital.entity.Bill;
import com.hospital.entity.PaymentStatus;

public interface BillService {

    Bill saveBill(Bill bill);

    List<Bill> getAllBills();

    Bill getBillById(Long id);

    Bill updateBill(Long id, Bill bill);

    void deleteBill(Long id);

    List<Bill> getBillsByPaymentStatus(PaymentStatus paymentStatus);
}