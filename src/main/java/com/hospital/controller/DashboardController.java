package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.DashboardDTO;
import com.hospital.service.DashboardService;
import com.hospital.util.ApiResponse;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardDTO> getDashboard() {

        return new ApiResponse<>(

                true,

                "Dashboard Loaded Successfully",

                dashboardService.getDashboard());

    }

}