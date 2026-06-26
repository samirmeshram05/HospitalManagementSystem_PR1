package com.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.Bill;
import com.hospital.entity.Patient;
import com.hospital.entity.PaymentStatus;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.BillRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.service.BillService;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Bill saveBill(Bill bill) {

		Long patientId = bill.getPatient().getPatientId();

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID : " + patientId));

		bill.setPatient(patient);

		double total = bill.getConsultationFee() + bill.getMedicineCharge() + bill.getLabCharge()
				+ bill.getOtherCharge();

		bill.setTotalAmount(total);

		return billRepository.save(bill);
	}

	@Override
	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	@Override
	public Bill getBillById(Long id) {

		return billRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bill not found with ID : " + id));
	}

	@Override
	public Bill updateBill(Long id, Bill bill) {

		Bill existing = billRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bill not found with ID : " + id));

		Long patientId = bill.getPatient().getPatientId();

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID : " + patientId));

		existing.setBillDate(bill.getBillDate());
		existing.setConsultationFee(bill.getConsultationFee());
		existing.setMedicineCharge(bill.getMedicineCharge());
		existing.setLabCharge(bill.getLabCharge());
		existing.setOtherCharge(bill.getOtherCharge());
		existing.setPaymentStatus(bill.getPaymentStatus());
		existing.setPatient(patient);

		double total = bill.getConsultationFee() + bill.getMedicineCharge() + bill.getLabCharge()
				+ bill.getOtherCharge();

		existing.setTotalAmount(total);

		return billRepository.save(existing);
	}

	@Override
	public void deleteBill(Long id) {
		Bill bill = billRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bill not found with ID : " + id));
		billRepository.delete(bill);
	}

	@Override
	public List<Bill> getBillsByPaymentStatus(PaymentStatus paymentStatus) {
		return billRepository.findByPaymentStatus(paymentStatus);

	}
}