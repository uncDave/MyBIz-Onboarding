package com.nownow.mybiz.onboarding.proxy.dto.onboarding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    private String businessName;
    private String businessAddress;
    private String businessState;
    private String businessLGA;
    private String businessWard;
    private String tinNumber;

    private List<AddDirectorRequest> directorRequests;

    private String pin;

    private String confirmPin;
}
