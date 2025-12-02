package com.nownow.mybiz.onboarding.proxy.dto.onboarding;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDirectorRequest {
    private String businessName;
    private String businessAddress;
    private String businessState;
    private String businessLGA;
    private String businessWard;
    private String tinNumber;
}
