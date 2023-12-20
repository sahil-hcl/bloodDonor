package com.hcl.bloodDonor.controller;

import com.hcl.bloodDonor.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
public class AdviceControllerBloodDonor {
    private String exceptionId = null;

    @ExceptionHandler(value = org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> methodNotfound(
            HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        exceptionId = UUID.randomUUID().toString();
        ErrorResponse errorResponse = ErrorResponse.builder().exceptionId(exceptionId)
                .message("Method Not Supported").timeStamp(Instant.now(Clock.systemUTC())).build();
        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,
                HttpStatus.METHOD_NOT_ALLOWED);
        return responseEntity;
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodNotfound(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        exceptionId = UUID.randomUUID().toString();
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (final ObjectError error : methodArgumentNotValidException.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        //final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        //return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
        ErrorResponse errorResponse = ErrorResponse.builder().exceptionId(exceptionId)
                .message(errors.toString()).timeStamp(Instant.now(Clock.systemUTC())).build();
        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,
                HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeException(Exception exception) {
        exceptionId = UUID.randomUUID().toString();
        ErrorResponse errorResponse = ErrorResponse.builder().exceptionId(exceptionId)
                .message(exception.getMessage()).timeStamp(Instant.now(Clock.systemUTC())).build();
        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,
                HttpStatus.NOT_FOUND);
        return responseEntity;
    }


}
