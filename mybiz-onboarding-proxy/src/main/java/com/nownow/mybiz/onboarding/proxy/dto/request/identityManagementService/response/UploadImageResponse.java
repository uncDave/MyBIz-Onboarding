package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadImageResponse {
    private String imageUrl;
}
