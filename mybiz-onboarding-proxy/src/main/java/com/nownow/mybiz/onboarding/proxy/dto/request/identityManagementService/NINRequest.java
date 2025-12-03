package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NINRequest {
    @Size(min = 11, max = 11, message = "NIN must be exactly 11 digits")
    @Pattern(regexp = "^[0-9]+$", message = "NIN must contain only digits")
    private String nin;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "date of birth is required")
    private String dateOfBirth;

}
