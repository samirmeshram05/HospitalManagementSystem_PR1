package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Bill;
import com.hospital.entity.PaymentStatus;
import com.hospital.service.BillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    // Generate Bill
    @PostMapping
    public Bill saveBill(@Valid @RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    // Get All Bills
    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    // Get Bill By Id
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    // Update Bill
    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @Valid @RequestBody Bill bill) {
        return billService.updateBill(id, bill);
    }

    // Delete Bill
    @DeleteMapping("/{id}")
    public String deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return "Bill Deleted Successfully";
    }
    
    @GetMapping("/paymentStatus/{status}")
    public List<Bill> getBillsByPaymentStatus( @PathVariable PaymentStatus status){
        return billService.getBillsByPaymentStatus(status);
    }

}