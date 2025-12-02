package com.nownow.mybiz.onboarding.proxy.services.mybiz.auth.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.LoginRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterIndividualRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterMultipleDirectorUserRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterSoleProprietorRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.response.AccountTypeResponse;
import com.nownow.mybiz.onboarding.proxy.services.mybiz.auth.AuthService;
import com.nownow.mybiz.onboarding.proxy.utils.ApiClientUtil;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nownow.mybiz.onboarding.proxy.utils.ResponseUtils.createFailureResponse;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final ApiClientUtil apiClientUtil;

    @Value("${onboard-individual.url}")
    private String individualUrl;


    @Value("${onboard-sole-proprietor.url}")
    private String soleProprietorUrl;

    @Value("${onboard-director.url}")
    private String directorUrl;

    @Value("${onboard-login.url}")
    private String loginUrl;

    @Value("${onboard-accountTypes.url}")
    private String proxyAccountTypesUrl;




    @Override
    public ResponseEntity<ApiResponse<?>> loginUser(LoginRequest request) {
        try {
            ResponseEntity<ApiResponse<?>> response = apiClientUtil.postWithoutToken(
                    loginUrl,
                    request,
                    new TypeReference<ApiResponse<?>>() {}
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed login attempt for username {}: {}", request.getPhone(), response.getBody());
                return ResponseEntity.status(response.getStatusCode())
                        .body(createFailureResponse("Error", "Login failed"));
            }

            return ResponseEntity.ok(response.getBody());
        } catch (Exception ex) {
            log.error("Exception during login for username {}: {}", request.getPhone(), ex.getMessage(), ex);
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", "Login failed"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> registerIndividualUser(RegisterIndividualRequest request) {
        try {
            ResponseEntity<ApiResponse<?>> response = apiClientUtil.postWithoutToken(
                    individualUrl,
                    request,
                    new TypeReference<ApiResponse<?>>() {}
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to register individual user: {}", response.getBody());
                return ResponseEntity.status(response.getStatusCode())
                        .body(createFailureResponse("Error", "Failed to register individual user"));
            }

            return ResponseEntity.ok(response.getBody());
        } catch (Exception ex) {
            log.error("Exception registering individual user: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", "Failed to register individual user"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> registerSoleProprietorUser(RegisterSoleProprietorRequest request) {
        try {
            ResponseEntity<ApiResponse<?>> response = apiClientUtil.postWithoutToken(
                    soleProprietorUrl,
                    request,
                    new TypeReference<ApiResponse<?>>() {}
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to register sole proprietor user: {}", response.getBody());
                return ResponseEntity.status(response.getStatusCode())
                        .body(createFailureResponse("Error", "Failed to register sole proprietor user"));
            }

            return ResponseEntity.ok(response.getBody());
        } catch (Exception ex) {
            log.error("Exception registering sole proprietor user: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", "Failed to register sole proprietor user"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> registerMultipleDirectorUser(RegisterMultipleDirectorUserRequest request) {
        try {
            ResponseEntity<ApiResponse<?>> response = apiClientUtil.postWithoutToken(
                    directorUrl,
                    request,
                    new TypeReference<ApiResponse<?>>() {}
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to register multiple director user: {}", response.getBody());
                return ResponseEntity.status(response.getStatusCode())
                        .body(createFailureResponse("Error", "Failed to register multiple director user"));
            }

            return ResponseEntity.ok(response.getBody());
        } catch (Exception ex) {
            log.error("Exception registering multiple director user: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", "Failed to register multiple director user"));
        }
    }

    @Override
    public ResponseEntity<List<AccountTypeResponse>> getProxyAccountTypes() {
        try {
            ResponseEntity<List<AccountTypeResponse>> response = apiClientUtil.getWithoutToken(
                    proxyAccountTypesUrl,
                    new TypeReference<List<AccountTypeResponse>>() {}
            );

            return ResponseEntity.ok(response.getBody());

        } catch (Exception ex) {
            log.error("Exception fetching account types from proxy: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
