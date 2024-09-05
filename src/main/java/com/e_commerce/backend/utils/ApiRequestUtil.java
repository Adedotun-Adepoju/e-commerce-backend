package com.e_commerce.backend.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class ApiRequestUtil {
    public static <T> ResponseEntity<T> makeGetRequest(String url, HttpHeaders headers, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
    }

    public static <T, R> ResponseEntity<T> makePostRequest(
            String url,
            HttpHeaders headers,
            R requestBody,
            Class<T> responseType
    ) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<R> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
    }
}
