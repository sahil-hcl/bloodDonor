package com.hcl.bloodDonor.service;

import com.hcl.bloodDonor.entity.BloodGroup;
import com.hcl.bloodDonor.repository.BloodGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodGroupService {

    @Autowired
    private BloodGroupRepository bloodGroupRepository;
    public BloodGroup saveBloodGroup(BloodGroup bloodGroup) {
        return bloodGroupRepository.save(bloodGroup);
    }

    public List<BloodGroup> getBloodGroups() {
        return bloodGroupRepository.findAll();
    }

    public BloodGroup getBloodGroupbyName(String bloodGroup) {
        return bloodGroupRepository.findByBloodGroup(bloodGroup);
    }
}
