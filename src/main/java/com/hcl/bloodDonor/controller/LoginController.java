package com.hcl.bloodDonor.controller;

import com.hcl.bloodDonor.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private LoginService service;
}
