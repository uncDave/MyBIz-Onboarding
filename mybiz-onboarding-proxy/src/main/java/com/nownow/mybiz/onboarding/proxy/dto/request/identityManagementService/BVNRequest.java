package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BVNRequest {
    @Pattern(
            regexp = "^[0-9]{11}$",
            message = "BVN must be exactly 11 digits"
    )
    private String bvn;



}
