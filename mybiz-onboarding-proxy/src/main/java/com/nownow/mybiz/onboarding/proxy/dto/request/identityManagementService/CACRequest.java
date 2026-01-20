package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CACRequest {


//    @Pattern(
//            regexp = "^RC[0-9]{7}$",
//            message = "cac must be in the format RC followed by 7 digits (e.g. RC0000000)"
//    )
    @NotBlank(message = "cac is required")
    private String cac;


}
