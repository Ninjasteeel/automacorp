package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.Services.AddressSearchService;
import com.emse.spring.automacorp.dto.ApiGouvAdress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    private final AddressSearchService addressSearchService;

    @Autowired
    public AddressController(AddressSearchService addressSearchService) {
        this.addressSearchService = addressSearchService;
    }

    @GetMapping("/search")
    public List<ApiGouvAdress> searchAddresses(@RequestParam List<String> keys) {
        // Use the AddressSearchService to perform the search
        return addressSearchService.searchAddresses(keys);
    }
}


