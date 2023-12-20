package com.hcl.bloodDonor.controller;

import com.hcl.bloodDonor.entity.BloodGroup;
import com.hcl.bloodDonor.service.BloodGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloodGroup")
public class BloodGroupController {
    @Autowired
    private BloodGroupService service;

    @PostMapping("/addBloodGroup")
    public BloodGroup addBloodGroup(@Valid @RequestBody BloodGroup bloodGroup){
        return service.saveBloodGroup(bloodGroup);
    }
    @GetMapping("/bloodGroups")
    public List<BloodGroup> findBloodGroupbyName(){
        return service.getBloodGroups();
    }

    @GetMapping("/bloodGroupByName/{bloodGroupName}")
    public BloodGroup findBloodGroupbyName(@PathVariable String bloodGroupName){
        return service.getBloodGroupbyName(bloodGroupName);
    }

}
