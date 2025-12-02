package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TINRequest {
    @Pattern(
            regexp = "^[0-9]{8}-[0-9]{4}$",
            message = "TIN must follow the format XXXXXXXX-XXXX (digits only)"
    )
    private String tin;
}
