package com.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.DashboardDTO;
import com.hospital.entity.AppointmentStatus;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DepartmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public DashboardDTO getDashboard() {

        DashboardDTO dashboard = new DashboardDTO();

        dashboard.setTotalPatients(patientRepository.count());

        dashboard.setTotalDoctors(doctorRepository.count());

        dashboard.setTotalDepartments(departmentRepository.count());

        dashboard.setTotalAppointments(appointmentRepository.count());

        dashboard.setBookedAppointments(
                appointmentRepository.countByAppointmentStatus(AppointmentStatus.BOOKED));

        dashboard.setCompletedAppointments(
                appointmentRepository.countByAppointmentStatus(AppointmentStatus.COMPLETED));

        dashboard.setCancelledAppointments(
                appointmentRepository.countByAppointmentStatus(AppointmentStatus.CANCELLED));

        return dashboard;
    }

}