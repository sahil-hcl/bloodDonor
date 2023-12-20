package com.hcl.bloodDonor.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bloodDonor.entity.BloodGroup;
import com.hcl.bloodDonor.service.BloodGroupService;
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
@WebMvcTest(BloodGroupController.class)
public class BloodGroupControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BloodGroupService service;
//    @InjectMocks
//    private AddressController addressController = new AddressController();
//
//    @Mock
//    private AddressService service;

    @Test
    public void testaddBloodGroup() throws Exception {
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setBloodGroupId(1);
        bloodGroup.setBloodGroupName("A+");

        String requestBody = new ObjectMapper().writeValueAsString(bloodGroup);

        given(service.saveBloodGroup(any())).willReturn(bloodGroup);

        MvcResult result = mvc.perform(post("/bloodGroup/addBloodGroup")
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        BloodGroup bloodGroupResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), BloodGroup.class);;
        assertEquals(bloodGroup.toString(), bloodGroupResult.toString(), "failure - strings are not equal");
    }
    @Test
    public void testfindAllBloodGroups() throws Exception {
        List<BloodGroup> bloodGroups = new ArrayList<>();

        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setBloodGroupId(1);
        bloodGroup.setBloodGroupName("A+");

        bloodGroups.add(bloodGroup);

        given(service.getBloodGroups()).willReturn(bloodGroups);

        MvcResult result = mvc.perform(get("/bloodGroup/bloodGroups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<BloodGroup> resultList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<BloodGroup>>(){});;
        assertEquals(bloodGroups.toString(), resultList.toString(), "failure - strings are not equal");
    }

    @Test
    public void testfindBloodGroupbyName() throws Exception {
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setBloodGroupId(1);
        bloodGroup.setBloodGroupName("A+");

        //String requestBody = new ObjectMapper().writeValueAsString(bloodGroup);

        given(service.getBloodGroupbyName(any())).willReturn(bloodGroup);

        MvcResult result = mvc.perform(get("/bloodGroup/bloodGroupByName/A")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        BloodGroup bloodGroupResult = new ObjectMapper().readValue(result.getResponse().getContentAsString(), BloodGroup.class);;
        assertEquals(bloodGroup.toString(), bloodGroupResult.toString(), "failure - strings are not equal");
    }
}
