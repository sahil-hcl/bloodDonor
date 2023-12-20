package com.hcl.bloodDonor.repository;

import com.hcl.bloodDonor.entity.Donor;
import com.hcl.bloodDonor.response.DonorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor,Integer> {
    @Query("Select new com.hcl.bloodDonor.response.DonorResponse(d.name as name, b.bloodGroupName as bloodGroup, d.mobileNumber as mobileNumber, a.city as city, a.district as district, a.state as state, a.country as country, d.availability as availability) from Donor d join address a on a.address_id=d.address.address_id join bloodGroup b on b.bloodGroupId=d.bloodGroup.bloodGroupId where b.bloodGroupName=:bloodGroup and a.country=:country and (:state is null or a.state = :state) and (:district is null or a.district = :district) and (:city is null or a.city = :city)")
    List<DonorResponse> findByBloodGroupAndCountry(@Param("bloodGroup")String bloodGroup, @Param("country")String country, @Param("state")String state, @Param("district")String district, @Param("city")String city);
}
