package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest companyRequest ,Long userId);


    List<CompanyResponse> getAllCompanies();

    CompanyResponse getCompanyById(Long id, Long userId);

    CompanyResponse updateCompany(CompanyRequest companyRequest,Long companyId);

    void deleteCompany(Long id);


    List<CompanyResponse> getAllCompaniesByUserId(Long userId);
}
