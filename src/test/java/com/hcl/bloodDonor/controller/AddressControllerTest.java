package com.hcl.bloodDonor.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bloodDonor.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressService service;
//    @InjectMocks
//    private AddressController addressController = new AddressController();
//
//    @Mock
//    private AddressService service;

    @Test
    public void testFindAllCountries() throws Exception {
        List<String> countries = Arrays.asList("india","nepal");

        given(service.getCountries() ).willReturn(countries);

        MvcResult result = mvc.perform(get("/address/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<String> resultList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});;
        assertEquals(countries.toString(), resultList.toString(), "failure - strings are not equal");
    }
    @Test
    public void testfindAllStatebyCountry() throws Exception {
        List<String> state = Arrays.asList("up");

        given(service.getStates("india") ).willReturn(state);

        MvcResult result = mvc.perform(get("/address/statesByCountry/india")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<String> resultList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});;
        assertEquals(state.toString(), resultList.toString(), "failure - strings are not equal");
    }

    @Test
    public void testfindAllDistrictsByState() throws Exception {
        List<String> district = Arrays.asList("varanasi");

        given(service.getDistricts("up","india") ).willReturn(district);

        MvcResult result = mvc.perform(get("/address/districtsByState/up/india")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<String> resultList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});;
        assertEquals(district.toString(), resultList.toString(), "failure - strings are not equal");
    }
    @Test
    public void testfindCityByDistrict() throws Exception {
        List<String> city = Arrays.asList("varanasi");

        given(service.getCity("varanasi","up","india") ).willReturn(city);

        MvcResult result = mvc.perform(get("/address/CitiesByDistrict/varanasi/up/india")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<String> resultList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){});;
        assertEquals(city.toString(), resultList.toString(), "failure - strings are not equal");
    }


}
