package com.lawzoom.companyservice.repository.companyRepo;


import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.companyModel.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType,Long> {
}
