package com.hcl.bloodDonor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Login implements Serializable {
    @Id
    private String userId;
    private String password;

    @OneToOne(mappedBy = "login")
    private Donor donor;
}
