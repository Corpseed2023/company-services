package com.lawzoom.companyservice.serviceImpl;


import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.repository.BusinessUnitRepository;
import com.lawzoom.companyservice.repository.CompanyRepository;
import com.lawzoom.companyservice.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {


    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public BusinessUnitResponse createBusinessUnit(BusinessUnitRequest businessUnitRequest, Long companyId) {

        Optional<Company> companyData = companyRepository.findById(companyId);


        if (companyData.isPresent()) {
            // Check if a business unit with the same address already exists
            BusinessUnit existingBusinessUnit = businessUnitRepository.findByAddress(businessUnitRequest.getAddress());

            if (existingBusinessUnit == null) {
                // Create a new BusinessUnit
                BusinessUnit newBusinessUnit = new BusinessUnit();

                // Set the properties based on the request
                newBusinessUnit.setAddress(businessUnitRequest.getAddress());
                newBusinessUnit.setCompany(companyData.get()); // Set the Company association
                newBusinessUnit.setBusinessActivity(businessUnitRequest.getBusinessActivity());
                newBusinessUnit.setCity(businessUnitRequest.getCity());
                newBusinessUnit.setLocatedAt(businessUnitRequest.getLocatedAt());
                newBusinessUnit.setPermanentEmployee(businessUnitRequest.getPermanentEmployee());
                newBusinessUnit.setContractEmployee(businessUnitRequest.getContractEmployee());
                newBusinessUnit.setCreatedAt(businessUnitRequest.getCreatedAt());
                newBusinessUnit.setUpdatedAt(businessUnitRequest.getUpdatedAt());
                newBusinessUnit.setEnable(businessUnitRequest.isEnable());
                newBusinessUnit.setDateRegistration(businessUnitRequest.getDateRegistration());
                newBusinessUnit.setStates(businessUnitRequest.getStates());
                newBusinessUnit.setGstNumber(businessUnitRequest.getGstNumber());


                // Save the new BusinessUnit
                BusinessUnit savedBusinessUnit = businessUnitRepository.save(newBusinessUnit);

                // Create and return a response
                BusinessUnitResponse response = new BusinessUnitResponse();
                response.setId(savedBusinessUnit.getId());
                response.setBusinessActivity(savedBusinessUnit.getBusinessActivity());
                response.setCity(savedBusinessUnit.getCity());
                response.setLocatedAt(savedBusinessUnit.getLocatedAt());
                response.setPermanentEmployee(savedBusinessUnit.getPermanentEmployee());
                response.setContractEmployee(savedBusinessUnit.getContractEmployee());
                response.setAddress(savedBusinessUnit.getAddress());
                response.setCreatedAt(savedBusinessUnit.getCreatedAt());
                response.setUpdatedAt(savedBusinessUnit.getUpdatedAt());
                response.setEnable(savedBusinessUnit.isEnable());
                response.setDateRegistration(savedBusinessUnit.getDateRegistration());
                response.setStates(savedBusinessUnit.getStates());
                response.setGstNumber(savedBusinessUnit.getGstNumber());

                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Business unit with the same address already exists");
            }
        }

        return null;
    }



    @Override
    public BusinessUnitResponse updateBusinessUnit(Long companyId, Long businessUnitId, BusinessUnitRequest businessUnitRequest) {
        // Check if the GST data exists
        Optional<Company> companyData = companyRepository.findById(companyId);

        if (!companyData.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with ID: " + companyId);
        }

        // Check if the business unit exists
        Optional<BusinessUnit> businessUnit = businessUnitRepository.findById(businessUnitId);
        if (!companyData.isPresent() || !businessUnit.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GST or Business Unit not found");
        }
        // Update the business unit data
        BusinessUnit updatedBusinessUnit = businessUnit.get();
        updatedBusinessUnit.setBusinessActivity(businessUnitRequest.getBusinessActivity());
        updatedBusinessUnit.setCity(businessUnitRequest.getCity());
        updatedBusinessUnit.setLocatedAt(businessUnitRequest.getLocatedAt());
        updatedBusinessUnit.setPermanentEmployee(businessUnitRequest.getPermanentEmployee());
        updatedBusinessUnit.setContractEmployee(businessUnitRequest.getContractEmployee());
        updatedBusinessUnit.setAddress(businessUnitRequest.getAddress());
        updatedBusinessUnit.setGstNumber(businessUnitRequest.getGstNumber());
        updatedBusinessUnit.setDateRegistration(businessUnitRequest.getDateRegistration());

        // Save the updated business unit
        BusinessUnit savedBusinessUnit = businessUnitRepository.save(updatedBusinessUnit);

        // Create a response manually
        BusinessUnitResponse response = new BusinessUnitResponse();
        response.setId(savedBusinessUnit.getId());
        response.setBusinessActivity(savedBusinessUnit.getBusinessActivity());
        response.setCity(savedBusinessUnit.getCity());
        response.setLocatedAt(savedBusinessUnit.getLocatedAt());
        response.setPermanentEmployee(savedBusinessUnit.getPermanentEmployee());
        response.setContractEmployee(savedBusinessUnit.getContractEmployee());
        response.setAddress(savedBusinessUnit.getAddress());
        response.setCreatedAt(savedBusinessUnit.getCreatedAt());
        response.setUpdatedAt(savedBusinessUnit.getUpdatedAt());
        response.setEnable(savedBusinessUnit.isEnable());
        response.setGstNumber(savedBusinessUnit.getGstNumber());
        response.setDateRegistration(savedBusinessUnit.getDateRegistration());


        return response;
    }

//
//    @Override
//    public BusinessUnitResponse getBusinessUnit(Long gstId, Long businessUnitId) {
//        // Check if the GST data exists
//        Optional<Company> companyData = companyRepository.findById(gstId);
//
//        // Check if the business unit exists
//        Optional<BusinessUnit> businessUnit = businessUnitRepository.findById(businessUnitId);
//        if (!companyData.isPresent() || !businessUnit.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GST or Business Unit not found");
//        }
//
//        // Create a response for the found business unit
//        BusinessUnit savedBusinessUnit = businessUnit.get();
//        BusinessUnitResponse response = new BusinessUnitResponse();
//        response.setId(savedBusinessUnit.getId());
//        response.setBusinessActivity(savedBusinessUnit.getBusinessActivity());
//        response.setCity(savedBusinessUnit.getCity());
//        response.setLocatedAt(savedBusinessUnit.getLocatedAt());
//        response.setPermanentEmployee(savedBusinessUnit.getPermanentEmployee());
//        response.setContractEmployee(savedBusinessUnit.getContractEmployee());
//        response.setAddress(savedBusinessUnit.getAddress());
//        response.setCreatedAt(savedBusinessUnit.getCreatedAt());
//        response.setUpdatedAt(savedBusinessUnit.getUpdatedAt());
//        response.setEnable(savedBusinessUnit.isEnable());
////        response.setGstId(savedBusinessUnit.getGst().getId());
////        response.setGstNumber(savedBusinessUnit.getGst().getGstNumber());
//////        response.setGstState(savedBusinessUnit.getGst().getState());
//
//        return response;
//    }
//
//

    @Override
    public List<BusinessUnitResponse> getAllBusinessUnits(Long companyId) {
        List<BusinessUnit> businessUnits = businessUnitRepository.findByCompanyId(companyId);
        List<BusinessUnitResponse> businessUnitResponses = new ArrayList<>();

        for (BusinessUnit businessUnit : businessUnits) {
            BusinessUnitResponse response = new BusinessUnitResponse();
            response.setId(businessUnit.getId());
            response.setBusinessActivity(businessUnit.getBusinessActivity());
            response.setCity(businessUnit.getCity());
            response.setLocatedAt(businessUnit.getLocatedAt());
            response.setPermanentEmployee(businessUnit.getPermanentEmployee());
            response.setContractEmployee(businessUnit.getContractEmployee());
            response.setAddress(businessUnit.getAddress());
            response.setCreatedAt(businessUnit.getCreatedAt());
            response.setUpdatedAt(businessUnit.getUpdatedAt());
            response.setEnable(businessUnit.isEnable());
            response.setGstNumber(businessUnit.getGstNumber());
            response.setDateRegistration(businessUnit.getDateRegistration());
            response.setStates(businessUnit.getStates());


            businessUnitResponses.add(response);
        }

        return businessUnitResponses;
    }
//
//    @Override
//    public void deleteBusinessUnit(Long gstId, Long businessUnitId) {
//        // Check if the GST data exists
//        if (!companyRepository.existsById(gstId)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GST not found");
//        }
//
//        // Check if the business unit exists
//        if (!businessUnitRepository.existsById(businessUnitId)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Business unit not found");
//        }
//
//        // Delete the business unit
//        businessUnitRepository.deleteById(businessUnitId);
//    }
}
