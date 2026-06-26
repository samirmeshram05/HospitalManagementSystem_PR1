package com.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.Appointment;
import com.hospital.entity.Prescription;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.PrescriptionRepository;
import com.hospital.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Prescription savePrescription(Prescription prescription) {

        Long appointmentId = prescription.getAppointment().getAppointmentId();

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with ID : " + appointmentId));

        prescription.setAppointment(appointment);

        return prescriptionRepository.save(prescription);
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription getPrescriptionById(Long id) {

        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Prescription not found with ID : " + id));
    }

    @Override
    public Prescription updatePrescription(Long id, Prescription prescription) {

        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Prescription not found with ID : " + id));

        existing.setDiagnosis(prescription.getDiagnosis());
        existing.setMedicine(prescription.getMedicine());
        existing.setDosage(prescription.getDosage());
        existing.setInstructions(prescription.getInstructions());

        Long appointmentId = prescription.getAppointment().getAppointmentId();

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with ID : " + appointmentId));

        existing.setAppointment(appointment);

        return prescriptionRepository.save(existing);
    }

    @Override
    public void deletePrescription(Long id) {

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Prescription not found with ID : " + id));

        prescriptionRepository.delete(prescription);
    }
}