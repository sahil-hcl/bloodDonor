package com.hcl.bloodDonor.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DonorRequest implements Serializable {
    @NotEmpty(message = "Invalid Name")
    private String name;
    @NotEmpty(message = "Invalid Blood Group")
    private String bloodGroup;
    @Size(min=10, max=14, message = "Invalid Mobile Number length")
    //@Pattern(regexp = "^[+]?(\\d{1,2})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$/", message = "Invalid Mobile Number Pattern")
    private String mobileNumber;
    @NotEmpty(message = "Invalid Country")
    private String country;
    @NotEmpty(message = "Invalid State")
    private String state;
    @NotEmpty(message = "Invalid District")
    private String district;
    @NotEmpty(message = "Invalid City")
    private String city;
    @NotNull(message = "Invalid Availability")
    private Boolean availability;
    @Email(message = "Invalid Email")
    private String email;
    @NotEmpty(message = "Invalid password")
    private String password;
    @NotNull(message = "Invalid Acknowledgement")
    private Boolean acknowledgement;

}
