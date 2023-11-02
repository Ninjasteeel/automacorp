package com.emse.spring.automacorp.Services;

import com.emse.spring.automacorp.dto.ApiGouvAdress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Service
public class AddressSearchService {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://example.com"; // Replace with the actual API base URL

    @Autowired
    public AddressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
    }

    public List<ApiGouvAdress> searchAddresses(List<String> keys) {
        String params = String.join("+", keys);
        String uri = UriComponentsBuilder
                .fromUriString("/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();

        ApiGouvAdress[] response = restTemplate.getForObject(uri, ApiGouvAdress[].class);

        return List.of(response); // Convert the array to a list
    }
}

