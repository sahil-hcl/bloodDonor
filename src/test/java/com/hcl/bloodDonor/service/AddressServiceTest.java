package com.hcl.bloodDonor.service;

import com.hcl.bloodDonor.controller.AddressController;
import com.hcl.bloodDonor.entity.Address;
import com.hcl.bloodDonor.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testGetCountries(){

        List<String>  countries = Arrays.asList("india","Nepal");
        given(addressRepository.findCountries()).willReturn(Arrays.asList("india","Nepal"));
        assertEquals(countries.toString(), addressService.getCountries().toString(), "failure - strings are not equal");
    }

    @Test
    public void testGetStates(){
        List<String>  states = Arrays.asList("up","mp");
        given(addressRepository.findStateList(any())).willReturn(Arrays.asList("up","mp"));
        assertEquals(states.toString(), addressService.getStates("india").toString(), "failure - strings are not equal");
    }

    @Test
    public void testGetDistricts(){
        List<String>  states = Arrays.asList("varanasi","lucknow");
        given(addressRepository.findDistrictList("up","india")).willReturn(Arrays.asList("varanasi","lucknow"));
        assertEquals(states.toString(), addressService.getDistricts("up","india").toString(), "failure - strings are not equal");
    }

    @Test
    public void testGetCity(){
        List<String>  states = Arrays.asList("varanasi");
        given(addressRepository.findCityList("varanasi","up","india")).willReturn(Arrays.asList("varanasi"));
        assertEquals(states.toString(), addressService.getCity("varanasi","up","india").toString(), "failure - strings are not equal");
    }

    @Test
    public void testGetAddressByCountryStateDistrictCity(){

        Address address = new Address();
        address.setAddress_id(21);
        address.setCity("varanasi");
        address.setCountry("india");
        address.setDistrict("varanasi");
        address.setState("up");
        address.setCountryCode("IND");
        given(addressRepository.findByCountryAndStateAndDistrictAndCity("india","up","varanasi","varanasi")).willReturn(address);
        assertEquals(address.toString(), addressService.getAddressByCountryStateDistrictCity("india","up","varanasi", "varanasi").toString(), "failure - strings are not equal");
    }
}
