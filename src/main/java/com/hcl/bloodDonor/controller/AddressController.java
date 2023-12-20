package com.hcl.bloodDonor.controller;

import com.hcl.bloodDonor.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService service;
    @Cacheable("addresses")
    @GetMapping("/countries")
    public List<String> findAllCountries(){
        return service.getCountries();
    }

    @GetMapping("/statesByCountry/{country}")
    public List<String> findAllStatebyCountry(@PathVariable String country){
        return service.getStates(country);
    }

    @GetMapping("/districtsByState/{state}/{country}")
    public List<String> findAllDistrictsByState(@PathVariable String state, @PathVariable String country){
        return service.getDistricts(state, country);
    }

    @GetMapping("/CitiesByDistrict/{district}/{state}/{country}")
    public List<String> findCityByDistrict(@PathVariable String district, @PathVariable String state, @PathVariable String country){
        return service.getCity(district, state, country);
    }

}
