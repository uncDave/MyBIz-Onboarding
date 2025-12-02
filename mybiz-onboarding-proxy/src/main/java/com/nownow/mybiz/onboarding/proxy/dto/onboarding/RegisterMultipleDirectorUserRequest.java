package com.nownow.mybiz.onboarding.proxy.dto.onboarding;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterMultipleDirectorUserRequest {
    private String phoneNo;
    private String email;
    private String cacNumber;
    private String cacDocumentUrl;
    private String verificationDocumentName;
    private String verificationDocumentUrl;
    private String utilityDocumentName;
    private String utilityDocumentUrl;
    private List<AddDirectorRequest> directorRequests;
    @Pattern(regexp = "^\\d{4}$", message = "PIN must be exactly 4 digits")
    private String pin;

    @Pattern(regexp = "^\\d{4}$", message = "PIN must be exactly 4 digits")
    private String confirmPin;
}
