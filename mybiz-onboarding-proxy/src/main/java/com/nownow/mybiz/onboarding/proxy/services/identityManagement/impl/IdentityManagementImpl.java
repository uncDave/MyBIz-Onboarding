package com.nownow.mybiz.onboarding.proxy.services.identityManagement.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.BVNRequest;
import com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.CACRequest;
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

    @Value("${verify-cac.service.url}")
    private String verifyCacServiceUrl;

    @Value("${upload-file.service.url}")
    private String uploadFileServiceUrl;

    @Value("${token.service.api-key}")
    private String apiKey;


    @Override
    public ResponseEntity<ApiResponse<?>> verifyBVN(BVNRequest request) {
        try {
            String url = verifyBvnServiceUrl;

            ResponseEntity<ApiResponse<Object>> response = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            // SUCCESS
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode())
                        .body(response.getBody());
            }

            // FAILURE
            return ResponseEntity.status(response.getStatusCode())
                    .body(response.getBody());

        } catch (Exception e) {
            log.error("Unexpected error during BVN verification", e);

            ApiResponse<?> error = ApiResponse.builder()
                    .status(false)
                    .message("Verification failed")
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.internalServerError().body(error);
        }
    }



    @Override
    public ResponseEntity<ApiResponse<?>> verifyNIN(NINRequest request) {
        try {
            String url = verifyNinServiceUrl;
            ResponseEntity<ApiResponse<Object>> response  = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            log.info("NIN verification request: {}", request);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode())
                        .body(response.getBody());
            }

            return ResponseEntity.status(response.getStatusCode())
                    .body(response.getBody());

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
            ResponseEntity<ApiResponse<Object>> response = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            log.info("TIN verification request: {}", request);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode())
                        .body(response.getBody());
            }

            return ResponseEntity.status(response.getStatusCode())
                    .body(response.getBody());

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
    public ResponseEntity<ApiResponse<?>> verifyCAC(CACRequest request) {
        try {
            String url = verifyCacServiceUrl;

            log.info("CAC verification request: {}", request);

            ResponseEntity<ApiResponse<Object>> response = apiClientUtil.post(
                    url,
                    request,
                    apiKey,
                    new TypeReference<ApiResponse<Object>>() {}
            );

            // 🔴 IMPORTANT GUARD
            if (response == null) {
                log.error("CAC verification failed - null response from downstream");

                return ResponseEntity.status(502).body(
                        ApiResponse.builder()
                                .status(false)
                                .message("CAC verification service unavailable")
                                .error("Downstream service returned no response")
                                .build()
                );
            }

            return ResponseEntity.status(response.getStatusCode())
                    .body(response.getBody());

        } catch (Exception e) {
            log.error("CAC verification failed", e);

            return ResponseEntity.internalServerError()
                    .body(ApiResponse.builder()
                            .status(false)
                            .message("Verification failed")
                            .error(e.getMessage())
                            .build());
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

            if (response.isStatus()) {
                return ResponseEntity.ok(response);
            }

            ApiResponse<?> genericError = ApiResponse.builder()
                    .status(false)
                    .message("File upload failed. Please try again later.")
                    .build();

            return ResponseEntity
                    .status(400)   // since no HTTP status available, use 400
                    .body(genericError);




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
