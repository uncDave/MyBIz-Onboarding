package com.nownow.mybiz.onboarding.proxy.controller.MybizController.auth;

import com.nownow.mybiz.onboarding.proxy.dto.onboarding.LoginRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterIndividualRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterMultipleDirectorUserRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.RegisterSoleProprietorRequest;
import com.nownow.mybiz.onboarding.proxy.dto.onboarding.response.AccountTypeResponse;
import com.nownow.mybiz.onboarding.proxy.services.mybiz.auth.AuthService;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("proxy/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request) {
        log.info("Login request for username: {}", request.getPhone());
        return authService.loginUser(request);
    }

    @PostMapping("/register/individual")
    public ResponseEntity<ApiResponse<?>> registerIndividual(@RequestBody RegisterIndividualRequest request) {
        log.info("Register individual user request: {}", request.getEmail());
        return authService.registerIndividualUser(request);
    }

    @PostMapping("/register/sole-proprietor")
    public ResponseEntity<ApiResponse<?>> registerSoleProprietor(@RequestBody RegisterSoleProprietorRequest request) {
        log.info("Register sole proprietor user request: {}", request.getEmail());
        return authService.registerSoleProprietorUser(request);
    }

    @PostMapping("/register/directors")
    public ResponseEntity<ApiResponse<?>> registerMultipleDirectors(@RequestBody RegisterMultipleDirectorUserRequest request) {
        log.info("Register multiple directors request for company: {}", request.getEmail());
        return authService.registerMultipleDirectorUser(request);
    }

    @GetMapping("/account-types")
    public ResponseEntity<List<AccountTypeResponse>> getAccountTypesFromProxy() {
        log.info("Fetching account types from proxy");
        return authService.getProxyAccountTypes();
    }

}
