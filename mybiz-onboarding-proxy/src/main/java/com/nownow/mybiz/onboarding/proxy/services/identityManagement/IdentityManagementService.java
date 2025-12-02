package com.nownow.mybiz.onboarding.proxy.services.identityManagement;

import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.BVNRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.NINRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.TINRequest;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IdentityManagementService {
    ResponseEntity<ApiResponse<?>> verifyBVN(BVNRequest request);
    ResponseEntity<ApiResponse<?>> verifyNIN(NINRequest request);
    ResponseEntity<ApiResponse<?>> verifyTIN(TINRequest request);
    ResponseEntity<ApiResponse<?>> uploadFile(MultipartFile file);
}
