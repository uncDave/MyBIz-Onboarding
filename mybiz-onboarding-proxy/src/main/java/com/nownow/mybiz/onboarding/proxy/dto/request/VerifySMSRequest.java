package com.nownow.mybiz.onboarding.proxy.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifySMSRequest {
    private String phone;
    private String token;
}
