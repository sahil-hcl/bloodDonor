package com.hcl.bloodDonor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Slf4j
public class BloodGroup implements Serializable{

    @Id
    @GeneratedValue
    private Integer bloodGroupId;
    @NotEmpty(message = "Invalid Blood Group")
    @Size(min=1, max=10, message = "Invalid Blood Group length")
    @Column(unique=true)
    private String bloodGroupName;
    @JsonIgnore
    @OneToMany(mappedBy = "bloodGroup")
    private List<Donor> donors;
}
