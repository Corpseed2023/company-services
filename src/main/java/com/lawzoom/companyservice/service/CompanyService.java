package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.companyDto.CompanyBusinessUnitDto;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest companyRequest ,Long userId);


    List<CompanyResponse> getAllCompanies();

    List<CompanyResponse> getCompaniesByUserId(Long userId);

    CompanyResponse updateCompany(CompanyRequest companyRequest,Long companyId);

    void deleteCompany(Long id);

    List<Map<String, Object>> getAllCompanyDetails();

    List<Map<String, Object>> getAllBusinessDetails();

    List<Map<String, Object>> getAllTeamDetails();
    List<Map<String, Object>> getAllCompanyDetailsV2();


    List<CompanyBusinessUnitDto> getCompanyUnitComplianceDetails();
}
