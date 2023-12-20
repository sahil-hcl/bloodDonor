package com.hcl.bloodDonor.repository;

import com.hcl.bloodDonor.entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BloodGroupRepository extends JpaRepository<BloodGroup,Integer> {
    @Query("select b from BloodGroup b where bloodGroupName =:bloodGroupName")
    BloodGroup findByBloodGroup(@Param("bloodGroupName") String bloodGroupName);
}
