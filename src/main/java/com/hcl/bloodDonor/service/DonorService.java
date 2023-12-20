package com.hcl.bloodDonor.service;

import com.hcl.bloodDonor.entity.Donor;
import com.hcl.bloodDonor.repository.DonorRepository;
import com.hcl.bloodDonor.response.DonorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<DonorResponse> getDonors(String bloodGroup, String country ,String state,String district, String city) {System.out.println(bloodGroup+country);
        log.info("Donor Service : bloodGroup : {}, country {}, state : {}, district : {}, city : {}",bloodGroup,country,state,district,city);
        return donorRepository.findByBloodGroupAndCountry(bloodGroup,country,state,district,city);
    }


}
