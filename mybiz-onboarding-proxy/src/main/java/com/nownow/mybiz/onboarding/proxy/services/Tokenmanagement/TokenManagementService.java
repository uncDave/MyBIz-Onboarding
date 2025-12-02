package com.nownow.mybiz.onboarding.proxy.services.Tokenmanagement;

import com.nownow.mybiz.onboarding.proxy.dto.request.EmailRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.SMSRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.VerifyEmailRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.VerifySMSRequest;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TokenManagementService {
    ResponseEntity<ApiResponse<?>> sendEmailToken(EmailRequest request);
    ResponseEntity<ApiResponse<?>> createPhoneToken(SMSRequest request);
    ResponseEntity<ApiResponse<?>> verifyEmailToken(VerifyEmailRequest verifyEmailRequest);
    ResponseEntity<ApiResponse<?>> verifySMSToken(VerifySMSRequest verifySMSRequest);
}
