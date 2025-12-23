package com.nownow.mybiz.onboarding.proxy.dto.onboarding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDirectorRequest {

    private String firstName;
    private String lastName;

    private String bvn;
    private String dob;

    private String gender;

    private Boolean pepStatus;
}
