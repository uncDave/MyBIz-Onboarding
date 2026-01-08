package com.nownow.mybiz.onboarding.proxy.services.mybiz.auth;

import com.nownow.mybiz.onboarding.proxy.dto.onboarding.LoginRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterIndividualRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterMultipleDirectorUserRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterSoleProprietorRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.response.AccountTypeResponse;
import com.nownow.mybiz.onboarding.proxy.dto.request.MileStoneRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.phoneNumberExistRequest;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthService {

    ResponseEntity<ApiResponse<?>> phoneExist(phoneNumberExistRequest request);
    ResponseEntity<ApiResponse<?>> deleteUser(phoneNumberExistRequest request);

    ResponseEntity<ApiResponse<?>> loginUser(LoginRequest request);
    ResponseEntity<ApiResponse<?>> logoutUser(String refreshToken);
    ResponseEntity<ApiResponse<?>> refreshToken(String refreshToken);
    ResponseEntity<ApiResponse<?>> registerIndividualUser(RegisterIndividualRequest request);
    ResponseEntity<ApiResponse<?>> registerSoleProprietorUser(RegisterSoleProprietorRequest request);
    ResponseEntity<ApiResponse<?>> registerMultipleDirectorUser(RegisterMultipleDirectorUserRequest request);
    ResponseEntity<List<AccountTypeResponse>> getProxyAccountTypes();
    ResponseEntity<ApiResponse<?>> saveMileStone(MileStoneRequest request);
    ResponseEntity<ApiResponse<?>> getMileStoneByPhoneNumber(String phoneNo);





}
