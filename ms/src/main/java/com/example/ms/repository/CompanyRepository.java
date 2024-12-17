package com.example.ms.repository;

import com.example.ms.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // SELECT * FROM COMPANY WHERE COMPANY_NAME = ?;
    Optional<Company> findByCompanyName(String companyName);
}
