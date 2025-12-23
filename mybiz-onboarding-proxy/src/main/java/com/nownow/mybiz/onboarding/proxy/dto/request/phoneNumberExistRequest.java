package com.nownow.mybiz.onboarding.proxy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class phoneNumberExistRequest {
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "Phone number must be exactly 11 digits"
    )
    private String phoneNo;
}
