package com.hcl.bloodDonor.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
@Data
@Builder
public class ErrorResponse implements Serializable {
    private String exceptionId;
    private String message;
    private Instant timeStamp;
}
