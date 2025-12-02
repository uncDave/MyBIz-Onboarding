package com.nownow.mybiz.onboarding.proxy.dto.tokenManagementService.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequest {
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(
            regexp = "^[0-9]{11}$",
            message = "Phone number must be exactly 11 digits"
    )
    private String phone;
}
