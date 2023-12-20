package com.hcl.bloodDonor.service;

import com.hcl.bloodDonor.entity.Address;
import com.hcl.bloodDonor.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<String> getCountries() {
        return addressRepository.findCountries();
    }

    public List<String> getStates(String country) {
        //  System.out.println(country);
       // System.out.println(addressRepository.findStateList(country));
        return addressRepository.findStateList(country);
    }

    public List<String> getDistricts(String state, String country) {
        return addressRepository.findDistrictList(state, country);
    }

    public List<String> getCity(String district, String state, String country) {
        return addressRepository.findCityList(district,state, country);
    }

    public Address getAddressByCountryStateDistrictCity(String country, String state, String district, String city) {
        return addressRepository.findByCountryAndStateAndDistrictAndCity(country,state,district,city);
    }
}
