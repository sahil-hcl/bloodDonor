package com.hcl.bloodDonor.repository;

import com.hcl.bloodDonor.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    @Query("select country from Address")
    List<String> findCountries();

    @Query("select a.state from Address a where country =:country")
    List<String> findStateList(@Param("country") String country);

    @Query("select a.district from Address a where state =:state and country =:country")
    List<String> findDistrictList(@Param("state") String state, @Param("country") String country);

    @Query("select a.city from Address a where district =:district and state =:state and country =:country")
    List<String> findCityList(@Param("district") String district, @Param("state") String state, @Param("country") String country);
    @Query("select a from Address a where country =:country and state =:state and district =:district and city=:city")
    Address findByCountryAndStateAndDistrictAndCity(@Param("country")String country,@Param("state") String state,@Param("district") String district,@Param("city") String city);
}
