package com.lawzoom.companyservice.repository;


import com.lawzoom.companyservice.model.companyModel.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByIdAndUserId(Long companyId, Long userId);


}
