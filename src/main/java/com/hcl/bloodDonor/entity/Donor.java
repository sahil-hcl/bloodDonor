package com.hcl.bloodDonor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcl.bloodDonor.request.DonorRequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Donor implements Serializable {
    @Id
    @GeneratedValue
    private Integer donor_id;
    private String name;
    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private Login login;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bloodGroup_id")
    private BloodGroup bloodGroup;
    private String mobileNumber;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private Boolean availability;
    private Boolean acknowledgement;

    public Donor(DonorRequest donorRequest) {
        this.name = donorRequest.getName();
        this.mobileNumber = donorRequest.getMobileNumber();
        this.availability = donorRequest.getAvailability();
        this.acknowledgement = donorRequest.getAcknowledgement();
    }
}
