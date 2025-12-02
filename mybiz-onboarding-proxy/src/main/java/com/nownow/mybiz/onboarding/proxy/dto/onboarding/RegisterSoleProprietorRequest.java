package com.nownow.mybiz.onboarding.proxy.dto.onboarding;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterSoleProprietorRequest {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String dob;
    private String gender;
    private String bvn;
    private Boolean pepStatus;


    private String businessName;
    private String businessAddress;
    private String businessState;
    private String businessLGA;
    private String businessWard;


    private String cacNumber;
    private String cacDocumentUrl;
    private String verificationDocumentName;
    private String verificationDocumentUrl;
    private String utilityDocumentName;
    private String utilityDocumentUrl;



    @Pattern(regexp = "^\\d{4}$", message = "PIN must be exactly 4 digits")
    private String pin;
    @Pattern(regexp = "^\\d{4}$", message = "PIN must be exactly 4 digits")
    private String confirmPin;
}
