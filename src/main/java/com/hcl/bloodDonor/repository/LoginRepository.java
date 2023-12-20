package com.hcl.bloodDonor.repository;

import com.hcl.bloodDonor.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,String> {
}
