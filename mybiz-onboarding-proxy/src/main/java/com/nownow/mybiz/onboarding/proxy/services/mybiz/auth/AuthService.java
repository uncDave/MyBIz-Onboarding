package com.nownow.mybiz.onboarding.proxy.services.mybiz.auth;

import com.nownow.mybiz.onboarding.proxy.dto.onboarding.LoginRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterIndividualRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterMultipleDirectorUserRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterSoleProprietorRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.response.AccountTypeResponse;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {


    ResponseEntity<ApiResponse<?>> loginUser(LoginRequest request);
    ResponseEntity<ApiResponse<?>> registerIndividualUser(RegisterIndividualRequest request);
    ResponseEntity<ApiResponse<?>> registerSoleProprietorUser(RegisterSoleProprietorRequest request);
    ResponseEntity<ApiResponse<?>> registerMultipleDirectorUser(RegisterMultipleDirectorUserRequest request);
    ResponseEntity<List<AccountTypeResponse>> getProxyAccountTypes();





}
