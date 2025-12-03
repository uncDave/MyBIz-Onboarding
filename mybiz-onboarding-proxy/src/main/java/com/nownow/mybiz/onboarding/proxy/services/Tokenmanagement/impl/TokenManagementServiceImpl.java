package com.nownow.mybiz.onboarding.proxy.services.Tokenmanagement.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nownow.mybiz.onboarding.proxy.dto.request.EmailRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.SMSRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.VerifyEmailRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.VerifySMSRequest;
import com.nownow.mybiz.onboarding.proxy.dto.tokenManagementService.request.TokenRequest;
import com.nownow.mybiz.onboarding.proxy.dto.tokenManagementService.request.VerifyTokenRequest;
import com.nownow.mybiz.onboarding.proxy.services.Tokenmanagement.TokenManagementService;
import com.nownow.mybiz.onboarding.proxy.utils.ApiClientUtil;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenManagementServiceImpl implements TokenManagementService {

    private final ApiClientUtil apiClientUtil;

    @Value("${token.service.url}")
    private String tokenServiceUrl;


    @Value("${verify-token.service.url}")
    private String verifyEmailTokenServiceUrl;

    @Value("${token.service.api-key}")
    private String apiKey;



    @Override
    public ResponseEntity<ApiResponse<?>> sendEmailToken(EmailRequest request) {
        try {
            TokenRequest emailOTPRequest = TokenRequest.builder()
                    .email(request.getEmail())
                    .build();

            log.info("this is the url : {}", tokenServiceUrl);
            log.info("this is the request : {}", request.getEmail());

            ResponseEntity<ApiResponse<?>> response = apiClientUtil.post(
                    tokenServiceUrl,
                    emailOTPRequest,
                    apiKey,
                    new TypeReference<ApiResponse<?>>() {}
            );

            log.info("This is the response : {}", response);

            if (!response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }

            return ResponseEntity.ok(response.getBody());

        } catch (Exception ex) {
            log.error("Failed to send email OTP: {}", ex.getMessage(), ex);

            return ResponseEntity.internalServerError()
                    .body(ApiResponse.<Object>builder()
                            .status(false)
                            .message("Failed to send email OTP")
                            .error(ex.getMessage())
                            .build());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> createPhoneToken(SMSRequest request) {
        // Implement similarly using apiClientUtil.post with TypeReference<ApiResponse<?>>
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> verifyEmailToken(VerifyEmailRequest verifyEmailRequest) {
        try {
            VerifyTokenRequest request = VerifyTokenRequest.builder()
                    .email(verifyEmailRequest.getEmail())
                    .token(verifyEmailRequest.getToken())
                    .build();

            ResponseEntity<ApiResponse<?>> response = apiClientUtil.post(
                    verifyEmailTokenServiceUrl,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<?>>() {}
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }

            return ResponseEntity.ok(response.getBody());

        } catch (Exception ex) {
            log.error("Failed to verify email token: {}", ex.getMessage(), ex);

            return ResponseEntity.internalServerError()
                    .body(ApiResponse.<Object>builder()
                            .status(false)
                            .message("Failed to verify email token")
                            .error(ex.getMessage())
                            .build());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> verifySMSToken(VerifySMSRequest verifySMSRequest) {
     try {
         VerifyTokenRequest request = VerifyTokenRequest.builder()
                 .email(verifySMSRequest.getPhone())
                 .token(verifySMSRequest.getToken())
                 .build();

         ResponseEntity<ApiResponse<?>> response = apiClientUtil.post(
                 verifyEmailTokenServiceUrl,
                 request,
                 apiKey,
                 new TypeReference<ApiResponse<?>>() {}
         );

         if (!response.getStatusCode().is2xxSuccessful()) {
             return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
         }

         return ResponseEntity.ok(response.getBody());

     } catch (Exception ex) {
         log.error("Failed to verify sms token: {}", ex.getMessage(), ex);

         return ResponseEntity.internalServerError()
                 .body(ApiResponse.<Object>builder()
                         .status(false)
                         .message("Failed to verify sms token")
                         .error(ex.getMessage())
                         .build());
     }
     }
}
