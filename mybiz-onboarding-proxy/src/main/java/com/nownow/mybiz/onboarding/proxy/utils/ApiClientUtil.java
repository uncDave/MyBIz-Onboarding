package com.nownow.mybiz.onboarding.proxy.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiClientUtil {
    private final ObjectMapper objectMapper;


    public <T> ResponseEntity<T> post(String url, Object requestBody, String token, TypeReference<T> typeRef) {

        WebClient webClient = WebClient.builder().build();

        try {
            return webClient.post()
                    .uri(url)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("X-API-KEY", token)
                    .bodyValue(requestBody)
                    .exchangeToMono(response ->
                            response.bodyToMono(String.class)
                                    .map(body -> {
                                        try {
                                            // Try to parse successful response
                                            T parsed = objectMapper.readValue(body, typeRef);
                                            return ResponseEntity
                                                    .status(response.statusCode())
                                                    .body(parsed);

                                        } catch (Exception ex) {

                                            // Parsing failed → return safe error wrapper
                                            ApiResponse<?> safeError = ResponseUtils.createFailureResponse(
                                                    "Parsing Error",
                                                    "Unable to process response from remote service"
                                            );

                                            @SuppressWarnings("unchecked")
                                            T casted = (T) safeError;

                                            return ResponseEntity
                                                    .status(response.statusCode())
                                                    .body(casted);
                                        }
                                    })
                    )
                    .block();

        } catch (Exception ex) {

            // Any other WebClient/network error
            ApiResponse<?> safeError = ResponseUtils.createFailureResponse(
                    "Service Error",
                    "Unable to complete request at the moment"
            );

            @SuppressWarnings("unchecked")
            T casted = (T) safeError;

            return ResponseEntity.status(500).body(casted);
        }
    }


    public <T> ResponseEntity<T> postWithoutToken(String url, Object requestBody, TypeReference<T> typeRef) {

        WebClient webClient = WebClient.builder().build();

        return webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .exchangeToMono(response ->
                        response.bodyToMono(String.class)
                                .map(body -> {
                                    try {
                                        T parsed = objectMapper.readValue(body, typeRef);
                                        return ResponseEntity.status(response.statusCode()).body(parsed);
                                    } catch (Exception ex) {
                                        throw new RuntimeException("Failed to parse response: " + body, ex);
                                    }
                                })
                )
                .block();
    }

    public <T> ResponseEntity<T> getWithoutToken(String url, TypeReference<T> typeRef) {

        WebClient webClient = WebClient.builder().build();

        try {
            return webClient.get()
                    .uri(url)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .exchangeToMono(response ->
                            response.bodyToMono(String.class)
                                    .map(body -> {
                                        try {
                                            T parsed = objectMapper.readValue(body, typeRef);
                                            return ResponseEntity.status(response.statusCode()).body(parsed);

                                        } catch (Exception ex) {
                                            ApiResponse<?> safeError = ResponseUtils.createFailureResponse(
                                                    "Parsing Error",
                                                    "Unable to process response from remote service"
                                            );

                                            @SuppressWarnings("unchecked")
                                            T castedResponse = (T) safeError;

                                            return ResponseEntity
                                                    .status(response.statusCode())
                                                    .body(castedResponse);
                                        }
                                    })
                    )
                    .block();

        } catch (Exception ex) {
            ApiResponse<?> safeError = ResponseUtils.createFailureResponse(
                    "Service Error",
                    "Unable to complete request at the moment"
            );

            @SuppressWarnings("unchecked")
            T castedResponse = (T) safeError;

            return ResponseEntity.status(500).body(castedResponse);
        }
    }




    public <T> T uploadMultipart(String url, MultipartFile file, String token, TypeReference<T> typeRef) {
        try {
            WebClient webClient = WebClient.builder().build();

            // Create the multipart body map
            org.springframework.util.LinkedMultiValueMap<String, Object> bodyMap = new org.springframework.util.LinkedMultiValueMap<>();
            bodyMap.add("file", file.getResource());

            String responseString = webClient.post()
                    .uri(url)
                    .header("X-API-KEY", token)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(bodyMap)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return objectMapper.readValue(responseString, typeRef);

        } catch (Exception e) {
            throw new RuntimeException("Multipart upload failed: " + e.getMessage(), e);
        }
    }




}
