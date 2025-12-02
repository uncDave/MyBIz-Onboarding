package com.nownow.mybiz.onboarding.proxy.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyEmailRequest {
    private String email;
    private String token;
}
