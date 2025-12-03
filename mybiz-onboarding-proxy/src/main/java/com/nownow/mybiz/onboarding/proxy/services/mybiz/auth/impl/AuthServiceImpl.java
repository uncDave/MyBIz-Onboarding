package com.nownow.mybiz.onboarding.proxy.services.mybiz.auth.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.LoginRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterIndividualRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterMultipleDirectorUserRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterSoleProprietorRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.response.AccountTypeResponse;
import com.nownow.mybiz.onboarding.proxy.dto.request.MileStoneRequest;
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

    @Value("${onboard-saveMileStone.url}")
    private String proxySaveMileStoneUrl;

    @Value("${onboard-getMileStone.url}")
    private String getMileStoneUrl;




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

            // ➤ If Service A returns error, forward Service A's error exactly
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to register individual user: {}", response.getBody());
                return ResponseEntity.status(response.getStatusCode())
                        .body(response.getBody()); // <-- forward actual error from A
            }

            // ➤ If successful, also forward Service A's success message
            return ResponseEntity.status(response.getStatusCode())
                    .body(response.getBody());

        } catch (Exception ex) {
            log.error("Exception registering individual user: {}", ex.getMessage(), ex);

            // generic internal error for service B
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", ex.getMessage()));
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

    @Override
    public ResponseEntity<ApiResponse<?>> saveMileStone(MileStoneRequest request) {
        try {
            ResponseEntity<ApiResponse<?>> response = apiClientUtil.postWithoutToken(
                    proxySaveMileStoneUrl,
                    request,
                    new TypeReference<ApiResponse<?>>() {}
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("failed to save user milestone: {}", response.getBody());
                return ResponseEntity.status(response.getStatusCode())
                        .body(createFailureResponse("Error", "failed to save user milestone: {}"));
            }

            return ResponseEntity.ok(response.getBody());
        } catch (Exception ex) {
            log.error("failed to save user mile stone: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", "failed to save user milestone"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getMileStoneByPhoneNumber(String phoneNo) {
        try {
            // Replace the placeholder with the actual phone number
            String urlWithPhone = getMileStoneUrl.replace("{phoneNo}", phoneNo);

            log.info("This is the url : {}", urlWithPhone);

            ResponseEntity<ApiResponse<?>> response = apiClientUtil.getWithoutToken(
                    urlWithPhone,
                    new TypeReference<ApiResponse<?>>() {}
            );

            return ResponseEntity.ok(response.getBody());
        } catch (Exception ex) {
            log.error("Failed to get user milestone for phone {}: {}", phoneNo, ex.getMessage(), ex);
            return ResponseEntity.internalServerError()
                    .body(createFailureResponse("Error", "Failed to get user milestone"));
        }
    }



}
