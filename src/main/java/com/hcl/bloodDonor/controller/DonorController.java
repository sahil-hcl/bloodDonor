package com.hcl.bloodDonor.controller;

import com.hcl.bloodDonor.entity.Address;
import com.hcl.bloodDonor.entity.BloodGroup;
import com.hcl.bloodDonor.entity.Donor;
import com.hcl.bloodDonor.entity.Login;
import com.hcl.bloodDonor.request.DonorRequest;
import com.hcl.bloodDonor.response.DonorResponse;
import com.hcl.bloodDonor.service.AddressService;
import com.hcl.bloodDonor.service.BloodGroupService;
import com.hcl.bloodDonor.service.DonorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor")
@Slf4j
public class DonorController {
    @Autowired
    private DonorService donorService;
    @Autowired
    private BloodGroupService bloodGroupService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/donorsByBloodGroupAndAddress")
    public List<DonorResponse> findAllSates(@RequestParam(name = "bloodGroup", required = true) String bloodGroup,
                                            @RequestParam(name = "country", required = true) String country,
                                            @RequestParam(name = "state", required = false) String state,
                                            @RequestParam(name = "district", required = false) String district,
                                            @RequestParam(name = "city", required = false) String city){
            log.info("bloodGroup : {}, country {}, state : {}, district : {}, city : {}",bloodGroup,country,state,district,city);
           // log.error("bloodGroup : {}, country {}",bloodGroup,country);
            return donorService.getDonors(bloodGroup,country,state,district,city);
    }

      @PostMapping("/saveDonor")
      public ResponseEntity<Donor> saveDonor(@Valid @RequestBody DonorRequest donorRequest){
          BloodGroup bloodGroup = bloodGroupService.getBloodGroupbyName(donorRequest.getBloodGroup());
          Donor donor = new Donor(donorRequest);
          Address address = addressService
                  .getAddressByCountryStateDistrictCity(
                          donorRequest.getCountry(),donorRequest.getState(),donorRequest.getDistrict(),
                          donorRequest.getCity());
          Login login = new Login();
          login.setUserId(donorRequest.getEmail());
          login.setPassword(donorRequest.getPassword());
          donor.setBloodGroup(bloodGroup);
          donor.setLogin(login);
          donor.setAddress(address);
          donor = donorService.saveDonor(donor);
          return new ResponseEntity<>(donor, HttpStatus.CREATED);
    }

}
