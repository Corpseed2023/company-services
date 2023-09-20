package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.exception.CompanyNotFoundException;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.repository.CompanyRepository;
import com.lawzoom.companyservice.service.CompanyService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        Company company = new Company();

        company.setFirstName(companyRequest.getFirstName());
        company.setLastName(companyRequest.getLastName());
        company.setCompanyType(companyRequest.getCompanyType());
        company.setCinNumber(companyRequest.getCompanyCINNumber());
        company.setBusinessActivityEmail(companyRequest.getBusinessActivityEmail());
        company.setCompanyName(companyRequest.getCompanyName());
        company.setCity(companyRequest.getCompanyCity());
        company.setAddress(companyRequest.getCompanyAddress());
        company.setDesignation(companyRequest.getDesignation());
        company.setContractEmployee(companyRequest.getContractEmployee());
        company.setCreatedAt(companyRequest.getCreatedAt());
        company.setTurnover(companyRequest.getCompanyTurnover());
        company.setGstNumber(companyRequest.getGstNumber());
        company.setBusinessActivity(companyRequest.getBusinessActivity());
        company.setEnable(companyRequest.isEnable());
        company.setLocatedAt(companyRequest.getLocatedAt());
        company.setState(companyRequest.getCompanyState());
        company.setPinCode(companyRequest.getCompanyPinCode());
        company.setPermanentEmployee(companyRequest.getPermanentEmployee());

        company = companyRepository.save(company);

        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyId(company.getId());
        companyResponse.setFirstName(company.getFirstName());
        companyResponse.setLastName(company.getLastName());
        companyResponse.setCompanyType(company.getCompanyType());
        companyResponse.setCompanyCINNumber(company.getCinNumber());
        companyResponse.setCompanyRegistrationNumber(company.getRegistrationNumber());
        companyResponse.setCompanyRegistrationDate(company.getRegistrationDate());
        companyResponse.setCompanyRemarks(company.getRemarks());
        companyResponse.setCompanyPinCode(company.getPinCode());
        companyResponse.setCompanyAddress(company.getAddress());
        companyResponse.setLocatedAt(company.getLocatedAt());
        companyResponse.setCreatedAt(company.getCreatedAt());
        companyResponse.setUpdatedAt(company.getUpdatedAt());
        companyResponse.setEnable(company.isEnable());
        companyResponse.setBusinessActivity(company.getBusinessActivity());
        companyResponse.setPermanentEmployee(company.getPermanentEmployee());
        companyResponse.setContractEmployee(company.getContractEmployee());
        companyResponse.setGstNumber(company.getGstNumber());
        companyResponse.setOperationUnitAddress(company.getOperationUnitAddress());


        return companyResponse;
    }


    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponse> companyResponses = new ArrayList<>();

        for (Company company : companies) {
            CompanyResponse response = new CompanyResponse();
            response.setCompanyId(company.getId());
            response.setCompanyType(company.getCompanyType());
            response.setCompanyName(company.getCompanyName());
            response.setFirstName(company.getFirstName());
            response.setLastName(company.getLastName());
            response.setBusinessActivityEmail(company.getBusinessActivityEmail());
            response.setDesignation(company.getDesignation());
            response.setCompanyState(company.getState());
            response.setCompanyCity(company.getCity());
            response.setCompanyRegistrationNumber(company.getRegistrationNumber());
            response.setCompanyRegistrationDate(company.getRegistrationDate());
            response.setCompanyCINNumber(company.getCinNumber());
            response.setCompanyRemarks(company.getRemarks());
            response.setCompanyPinCode(company.getPinCode());
            response.setCompanyAddress(company.getAddress());
            response.setCompanyTurnover(company.getTurnover());
            response.setLocatedAt(company.getLocatedAt());
            response.setCreatedAt(company.getCreatedAt());
            response.setUpdatedAt(company.getUpdatedAt());
            response.setEnable(company.isEnable());
            response.setBusinessActivity(company.getBusinessActivity());
            response.setPermanentEmployee(company.getPermanentEmployee());
            response.setContractEmployee(company.getContractEmployee());
            response.setGstNumber(company.getGstNumber());
            response.setOperationUnitAddress(company.getOperationUnitAddress());

            companyResponses.add(response);
        }

        return companyResponses;
    }


    @Override
    public CompanyResponse getCompanyById(Long id, Long userId) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found"));

        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setCompanyCINNumber(company.getCinNumber());
        companyResponse.setFirstName(company.getFirstName());
        companyResponse.setLastName(company.getLastName());
        companyResponse.setBusinessActivityEmail(company.getBusinessActivityEmail());

        return companyResponse;
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest companyRequest) {

        Optional<Company> company = companyRepository.findById(companyRequest.getCompanyId());

        if (company != null) {
            Company companyData = company.get();

            companyData.setCompanyName(companyRequest.getCompanyName());
            companyData.setAddress(companyRequest.getCompanyAddress());
            companyData.setCompanyType(companyRequest.getCompanyType());
            companyData.setBusinessActivityEmail(companyRequest.getBusinessActivityEmail());

            Company savedData= this.companyRepository.save(companyData);

            CompanyResponse companyResponse = new CompanyResponse();

            companyResponse.setCompanyAddress(companyData.getAddress());
            companyResponse.setCompanyAddress(companyData.getAddress());
            companyResponse.setCompanyType(companyData.getCompanyType());

            return companyResponse;

        }
        return null;
    }

    @Override
    public void deleteCompany(Long id) throws CompanyNotFoundException {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            companyRepository.delete(company);
        } else {
            throw new CompanyNotFoundException("Company with ID " + id + " not found");
        }
    }
}




