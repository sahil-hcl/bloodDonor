package com.hcl.bloodDonor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueCityDistrictStateCountry", columnNames = { "city", "district","state","country" })})
public class Address implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Integer address_id;
    private String city;
    private String district;
    private String state;
    private String countryCode;
    private String country;

    @OneToMany(mappedBy = "address")
    private List<Donor> donors;
}
