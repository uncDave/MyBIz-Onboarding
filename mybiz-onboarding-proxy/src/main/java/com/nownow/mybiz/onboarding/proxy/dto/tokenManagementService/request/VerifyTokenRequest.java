package com.nownow.mybiz.onboarding.proxy.dto.tokenManagementService.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyTokenRequest {
    private String email;
    private String phone;
    private String token;
}
