package com.nownow.mybiz.onboarding.proxy.dto.onboarding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterIndividualRequest {
    @NotNull(message = "first name required")
    @NotBlank(message = "first name required")
    private String firstName;
    @NotNull(message = "last mame required")
    @NotBlank(message = "last mame required")
    private String lastName;
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "Phone number must be exactly 11 digits"
    )
    private String phoneNo;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Date of birth is required")
    private String dob;
    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "NIN is required")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "NIN must be exactly 11 digits"
    )
    private String nin;
    @NotBlank(message = "BVN is required")
    @Pattern(
            regexp = "^\\d{11}$",
            message = "BVN must be exactly 11 digits"
    )
    private String bvn;
    @NotNull(message = "PEP status is required")
    private Boolean pepStatus;

    @NotBlank(message = "Business name is required")
    private String businessName;
    @NotBlank(message = "Business address is required")
    private String businessAddress;
    @NotBlank(message = "Business state is required")
    private String businessState;
    @NotBlank(message = "Business LGA is required")
    private String businessLGA;
    @NotBlank(message = "Business ward is required")
    private String businessWard;

    @NotBlank(message = "Verification document name is required")
    private String verificationDocumentName;
    @NotBlank(message = "Verification document URL is required")
    private String verificationDocumentUrl;
    @NotBlank(message = "Utility document name is required")
    private String utilityDocumentName;
    @NotBlank(message = "Utility document URL is required")
    private String utilityDocumentUrl;
    @Pattern(regexp = "^\\d{4}$", message = "PIN must be exactly 4 digits")
    private String pin;
    @Pattern(regexp = "^\\d{4}$", message = "PIN must be exactly 4 digits")
    private String confirmPin;
}
