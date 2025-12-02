package com.nownow.mybiz.onboarding.proxy.dto.request.identityManagementService.response;

import lombok.Data;

import java.util.List;

@Data
public class TinLookupResponse {
    private boolean success;
    private int statusCode;
    private String message;
    private DataBody data;
    private List<Object> links;

    @Data
    public static class DataBody {
        private String id;
        private String status;
        private String businessId;
        private String parentId;
        private boolean isConsent;
        private String type;
        private String name;
        private String registrationNumber;
        private String tin;
        private String jtbTin;
        private String taxOffice;
        private String email;
        private String phone;
        private String requestedAt;
        private String createdAt;
        private RequestedBy requestedBy;

        @Data
        public static class RequestedBy {
            private String firstName;
            private String lastName;
            private String middleName;
            private String id;
        }
    }
}

