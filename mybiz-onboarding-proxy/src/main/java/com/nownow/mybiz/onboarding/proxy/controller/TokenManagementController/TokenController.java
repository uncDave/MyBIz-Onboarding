package com.nownow.mybiz.onboarding.proxy.controller.TokenManagementController;

import com.nownow.mybiz.onboarding.proxy.dto.request.EmailRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.VerifyEmailRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.VerifySMSRequest;
import com.nownow.mybiz.onboarding.proxy.services.Tokenmanagement.TokenManagementService;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("proxy/api/v1/token")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class TokenController {

    private final TokenManagementService tokenManagementService;

    @PostMapping("/email/send")
    public ResponseEntity<ApiResponse<?>> emailToken (@Valid @RequestBody EmailRequest request) {
        log.info("Email token verification controller...");
        return tokenManagementService.sendEmailToken(request);
    }


    @PostMapping("/email/verify")
    public ResponseEntity<ApiResponse<?>> verifyEmailToken (@Valid @RequestBody VerifyEmailRequest request) {
        log.info("verify email otp...");
        return tokenManagementService.verifyEmailToken(request);
    }

    @PostMapping("/sms/verify")
    public ResponseEntity<ApiResponse<?>> verifySMSToken (@Valid @RequestBody VerifySMSRequest request) {
        log.info("verify sms otp...");
        return tokenManagementService.verifySMSToken(request);
    }


}
