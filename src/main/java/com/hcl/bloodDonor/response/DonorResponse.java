package com.hcl.bloodDonor.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DonorResponse implements Serializable {
    private String name;
    private String bloodGroup;
    private String mobile_number;
    private String city;
    private String district;
    private String state;
    private String country;
    private Boolean availability;
}
