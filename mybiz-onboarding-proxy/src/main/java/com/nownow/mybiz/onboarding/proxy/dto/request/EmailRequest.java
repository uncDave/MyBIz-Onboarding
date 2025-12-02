package com.nownow.mybiz.onboarding.proxy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {
    @Email(message = "Invalid email format")
    private String email;

}
