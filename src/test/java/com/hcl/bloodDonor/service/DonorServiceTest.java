package com.hcl.bloodDonor.service;

import com.hcl.bloodDonor.repository.AddressRepository;
import com.hcl.bloodDonor.repository.DonorRepository;
import com.hcl.bloodDonor.response.DonorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DonorServiceTest {
    @Mock
    private DonorRepository donorRepository;

    @InjectMocks
    private DonorService donorService;

    @Test
    public void testGetDonors(){

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
        given(donorRepository.findByBloodGroupAndCountry(any(),any(), any(),any(),any())).willReturn(donorResponses);
        assertEquals(donorResponses.toString(), donorService.getDonors("A+","india","up","varanasi",null).toString(), "failure - strings are not equal");
    }

}
