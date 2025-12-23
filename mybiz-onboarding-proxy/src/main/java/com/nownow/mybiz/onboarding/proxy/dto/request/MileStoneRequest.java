package com.nownow.mybiz.onboarding.proxy.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MileStoneRequest {
    @NotBlank(message = "phoneNo is required")
    private String phoneNo;

    @NotBlank(message = "role is required")
    private String userRole;

    @NotBlank(message = "mileStone is required")
    private String mileStone;

    private Map<String, Object> payload;
}
