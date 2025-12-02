package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class YouVerifyNinResponse {
    private boolean success;
    private int statusCode;
    private String message;
    private DataDetail data;
    private List<Object> links;

    @Data
    @NoArgsConstructor
    public static class DataDetail {
        private String id;
        private Address address;
        private String parentId;
        private String status;
        private String reason;
        private boolean dataValidation;
        private boolean selfieValidation;
        private String firstName;
        private String middleName;
        private String lastName;
        private String image;
        private String signature;
        private String mobile;
        private String email;
        private String birthState;
        private String nokState;
        private String religion;
        private String birthLGA;
        private String birthCountry;
        private String dateOfBirth;
        private boolean isConsent;
        private String idNumber;
        private String businessId;
        private String type;
        private boolean allValidationPassed;
        private String gender;
        private String requestedAt;
        private String requestedById;
        private String country;
        private String createdAt;
        private String lastModifiedAt;
        private Map<String, Object> metadata;
        private RequestedBy requestedBy;
    }

    @Data
    @NoArgsConstructor
    public static class Address {
        private String town;
        private String lga;
        private String state;
        private String addressLine;
    }

    @Data
    @NoArgsConstructor
    public static class RequestedBy {
        private String firstName;
        private String lastName;
        private String middleName;
        private String id;
    }
}
