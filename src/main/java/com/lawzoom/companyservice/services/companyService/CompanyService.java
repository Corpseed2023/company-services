package com.lawzoom.companyservice.services.companyService;

import com.lawzoom.companyservice.dto.companyDto.CompanyBusinessUnitDto;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest companyRequest ,Long userId);

    List<CompanyResponse> getAllCompanies();

    List<CompanyResponse> getCompaniesByUserId(Long userId);

    CompanyResponse updateCompany(CompanyRequest companyRequest,Long companyId);

    void deleteCompany(Long id);

    List<CompanyBusinessUnitDto> getCompanyUnitComplianceDetails(Long userId);
}
