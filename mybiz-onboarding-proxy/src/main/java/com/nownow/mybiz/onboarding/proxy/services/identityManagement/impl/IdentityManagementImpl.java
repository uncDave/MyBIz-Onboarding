package com.nownow.mybiz.onboarding.proxy.services.identityManagement.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.BVNRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.NINRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.TINRequest;
import com.nownow.mybiz.onboarding.proxy.services.identityManagement.IdentityManagementService;
import com.nownow.mybiz.onboarding.proxy.utils.ApiClientUtil;
import com.nownow.mybiz.onboarding.proxy.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Slf4j
public class IdentityManagementImpl implements IdentityManagementService {
    private final ApiClientUtil apiClientUtil;

    @Value("${verify-bvn.service.url}")
    private String verifyBvnServiceUrl;


    @Value("${verify-tin.service.url}")
    private String verifyTinServiceUrl;

    @Value("${verify-nin.service.url}")
    private String verifyNinServiceUrl;

    @Value("${upload-file.service.url}")
    private String uploadFileServiceUrl;

    @Value("${token.service.api-key}")
    private String apiKey;


    @Override
    public ResponseEntity<ApiResponse<?>> verifyBVN(BVNRequest request) {
        try {
            String url = verifyBvnServiceUrl;
            Object response = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            log.info("this is the request : {}", request);

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(true)
                            .message("BVN verification completed")
                            .data(response)
                            .build()
            );

        } catch (Exception e) {
            log.error("BVN verification failed", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.builder()
                            .status(false)
                            .message("Verification failed")
                            .error(e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> verifyNIN(NINRequest request) {
        try {
            String url = verifyNinServiceUrl;
            Object response = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            log.info("NIN verification request: {}", request);

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(true)
                            .message("NIN verification completed")
                            .data(response)
                            .build()
            );

        } catch (Exception e) {
            log.error("NIN verification failed", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.builder()
                            .status(false)
                            .message("Verification failed")
                            .error(e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> verifyTIN(TINRequest request) {
        try {
            String url = verifyTinServiceUrl;
            Object response = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            log.info("TIN verification request: {}", request);

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(true)
                            .message("TIN verification completed")
                            .data(response)
                            .build()
            );

        } catch (Exception e) {
            log.error("TIN verification failed", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.builder()
                            .status(false)
                            .message("Verification failed")
                            .error(e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> uploadFile(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .status(false)
                                .message("File is required")
                                .build());
            }

            // Forward the file to the underlying service
            ApiResponse<?> response = apiClientUtil.uploadMultipart(
                    uploadFileServiceUrl,
                    file,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(true)
                            .message("File uploaded successfully")
                            .data(response)
                            .build()
            );

        } catch (Exception e) {
            log.error("File upload failed", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.builder()
                            .status(false)
                            .message("File upload failed")
                            .error(e.getMessage())
                            .build());
        }
    }



}
