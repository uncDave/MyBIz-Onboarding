package com.nownow.mybiz.onboarding.proxy.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SMSRequest {
    @Pattern(
            regexp = "^[0-9]{11}$",
            message = "Phone number must be exactly 11 digits")
    private String phone;
}
