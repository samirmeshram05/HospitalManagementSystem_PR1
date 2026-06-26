package com.hospital.service;

import com.hospital.dto.LoginRequest;
import com.hospital.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}