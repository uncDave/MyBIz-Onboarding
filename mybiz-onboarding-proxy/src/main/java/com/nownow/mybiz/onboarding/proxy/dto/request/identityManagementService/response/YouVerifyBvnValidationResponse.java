package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class YouVerifyBvnValidationResponse {

    private boolean success;
    private int statusCode;
    private String message;
    private DataDetail data;
    private List<Link> links;

    @Data
    @NoArgsConstructor
    public static class DataDetail {
        private String id;
        private String parentId;
        private String status;
        private String reason;
        private boolean dataValidation;
        private boolean selfieValidation;
        private String firstName;
        private String middleName;
        private String lastName;
        private String image;
        private String enrollmentBranch;
        private String enrollmentInstitution;
        private String mobile;
        private String dateOfBirth;
        private boolean isConsent;
        private String idNumber;
        private String nin;
        private boolean shouldRetrivedNin;
        private String businessId;
        private String type;
        private boolean allValidationPassed;
        private String gender;
        private String requestedAt;
        private String requestedById;
        private String country;
        private String createdAt;
        private String lastModifiedAt;
        private String adverseMediaReport;
        private String amlReport;
        private String entityId;
        private Map<String, String> metadata;
        private RequestedBy requestedBy;
    }

    @Data
    @NoArgsConstructor
    public static class RequestedBy {
        private String firstName;
        private String lastName;
        private String middleName;
        private String id;
    }

    @Data
    @NoArgsConstructor
    public static class Link {
    }
}
