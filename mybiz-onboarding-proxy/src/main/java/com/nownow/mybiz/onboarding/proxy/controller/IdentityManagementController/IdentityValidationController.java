package com.nownow.mybiz.onboarding.proxy.controller.IdentityManagementController;

import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.BVNRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.CACRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.NINRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.TINRequest;
import com.nownow.mybiz.onboarding.proxy.services.identityManagement.IdentityManagementService;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("proxy/api/v1/identity")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class IdentityValidationController {

    private final IdentityManagementService identityManagementService;


    @PostMapping("/bvn/verify")
    public ResponseEntity<ApiResponse<?>> verifyBVN (@Valid @RequestBody BVNRequest request) {
        log.info("bvn verification controller...");
        return identityManagementService.verifyBVN(request);
    }

    @PostMapping("/nin/verify")
    public ResponseEntity<ApiResponse<?>> verifyNIN(@Valid @RequestBody NINRequest request) {
        log.info("NIN verification controller called: {}", request);
        return identityManagementService.verifyNIN(request);
    }

    @PostMapping("/tin/verify")
    public ResponseEntity<ApiResponse<?>> verifyTIN(@Valid @RequestBody TINRequest request) {
        log.info("TIN verification controller called: {}", request);
        return identityManagementService.verifyTIN(request);
    }


    @PostMapping("/cac/verify")
    public ResponseEntity<ApiResponse<?>> verifyCAC(@Valid @RequestBody CACRequest request) {
        log.info("CAC verification controller called: {}", request);
        return identityManagementService.verifyCAC(request);
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<?>> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("File upload controller called: filename={}", file.getOriginalFilename());
        return identityManagementService.uploadFile(file);
    }
}
