package com.nownow.mybiz.onboarding.proxy.dto.onboarding.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountTypeResponse {
    private Integer code;
    private String name;
    private String description;
}
