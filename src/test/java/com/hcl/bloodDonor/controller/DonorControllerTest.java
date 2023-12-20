package com.hcl.bloodDonor.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bloodDonor.entity.Address;
import com.hcl.bloodDonor.entity.BloodGroup;
import com.hcl.bloodDonor.entity.Donor;
import com.hcl.bloodDonor.entity.Login;
import com.hcl.bloodDonor.request.DonorRequest;
import com.hcl.bloodDonor.response.DonorResponse;
import com.hcl.bloodDonor.service.AddressService;
import com.hcl.bloodDonor.service.BloodGroupService;
import com.hcl.bloodDonor.service.DonorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DonorController.class)
public class DonorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DonorService donorService;
    @MockBean
    private BloodGroupService bloodGroupService;
    @MockBean
    private AddressService addressService;

    @Test
    public void testFindAllSates() throws Exception {
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setBloodGroupId(1);
        bloodGroup.setBloodGroupName("A+");

        DonorResponse donorResponse = new DonorResponse();
        donorResponse.setBloodGroup("A+");
        donorResponse.setName("Sahil");
        donorResponse.setCity("varanasi");
        donorResponse.setDistrict("varanasi");
        donorResponse.setState("up");
        donorResponse.setCountry("india");
        donorResponse.setMobile_number("9876543210");
        donorResponse.setAvailability(true);

        List<DonorResponse> donorResponses = new ArrayList<>();
        donorResponses.add(donorResponse);

        String requestBody = new ObjectMapper().writeValueAsString(bloodGroup);

        given(donorService.getDonors(any(),any(),any(),any(),any())).willReturn(donorResponses);

        MvcResult result = mvc.perform(get("/donor/donorsByBloodGroupAndAddress")
                        .contentType(MediaType.APPLICATION_JSON).param("bloodGroup", "A+")
                .param("country", "india") )
                .andExpect(status().isOk())
                .andReturn();
        List<DonorResponse> donorResponsesResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<DonorResponse>>(){});
        assertEquals(donorResponses.toString(), donorResponsesResult.toString(), "failure - strings are not equal");
    }
    @Test
    public void testsaveDonor() throws Exception {

        DonorRequest donorRequest = new DonorRequest();

        donorRequest.setName("Sahil");
        donorRequest.setBloodGroup("A+");
        donorRequest.setMobileNumber("9876543210");
        donorRequest.setEmail("asdfgh@jkl.com");
        donorRequest.setPassword("password");
        donorRequest.setCity("varanasi");
        donorRequest.setDistrict("varanasi");
        donorRequest.setState("up");
        donorRequest.setCountry("india");
        donorRequest.setAvailability(true);
        donorRequest.setAcknowledgement(true);

        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setBloodGroupId(1);
        bloodGroup.setBloodGroupName("A+");

        Address address = new Address();
        address.setAddress_id(21);
        address.setCity("varanasi");
        address.setCountry("india");
        address.setDistrict("varanasi");
        address.setState("up");
        address.setCountryCode("IND");

        Login login = new Login();
        login.setUserId(donorRequest.getEmail());
        login.setPassword(donorRequest.getPassword());

        Donor donor = new Donor(donorRequest);
        donor.setAddress(address);
        donor.setBloodGroup(bloodGroup);
        donor.setLogin(login);

        String requestBody = new ObjectMapper().writeValueAsString(donorRequest);

        given(bloodGroupService.getBloodGroupbyName(any())).willReturn(bloodGroup);
        given(addressService.getAddressByCountryStateDistrictCity(any(),any(),any(),any())).willReturn(address);
        given(donorService.saveDonor(any())).willReturn(donor);
        MvcResult result = mvc.perform(post("/donor/saveDonor")
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();

        Donor resultMap = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Donor.class);
        String expected = new ObjectMapper().writeValueAsString(donor);
        String actual = new ObjectMapper().writeValueAsString(resultMap);
        assertEquals(expected,actual, "failure - strings are not equal");
    }

//    @Test
//    public void testFindBloodGroupByName() throws Exception {
//        BloodGroup bloodGroup = new BloodGroup();
//        bloodGroup.setBloodGroupId(1);
//        bloodGroup.setBloodGroupName("A+");
//
//        Address address = new Address();
//        address.setAddress_id(21);
//        address.setCity("varanasi");
//        address.setCountry("india");
//        address.setDistrict("varanasi");
//        address.setState("up");
//        address.setCountryCode("IND");
//
//       // Donor donor = new Donor(
//        //String requestBody = new ObjectMapper().writeValueAsString(bloodGroup);
//
//        given(bloodGroupService.getBloodGroupbyName(any())).willReturn(bloodGroup);
//        given(addressService.getAddressbyCountryStateDistrictCity(any(),any(),any(),any())).willReturn(address);
//        //given(donorService.saveDonor(any())).willReturn(donor);
//        MvcResult result = mvc.perform(post("/donor/bloodGroup")
//                        .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk())
//                        .andReturn();
//        BloodGroup bloodGroupResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), BloodGroup.class);;
//        assertEquals(bloodGroup.toString(), bloodGroupResult.toString(), "failure - strings are not equal");
//    }
}
