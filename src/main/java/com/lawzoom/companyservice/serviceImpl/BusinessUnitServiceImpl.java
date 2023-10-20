package com.lawzoom.companyservice.serviceImpl;


import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.repository.BusinessUnitRepository;
import com.lawzoom.companyservice.repository.GstRepository;
import com.lawzoom.companyservice.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    @Autowired
    private GstRepository gstRepository;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Override
    public BusinessUnitResponse createBusinessUnit(BusinessUnitRequest businessUnitRequest, Long gstId) {
        // Check if the GST data exists
        Optional<Gst> gstData = gstRepository.findById(gstId);

        if (gstData.isPresent()) {
            // Check if a business unit with the same address already exists
            BusinessUnit existingBusinessUnit = businessUnitRepository.findByAddress(businessUnitRequest.getAddress());

            if (existingBusinessUnit == null) {
                // Create a new BusinessUnit
                BusinessUnit newBusinessUnit = new BusinessUnit();

                // Set the properties based on the request
                newBusinessUnit.setAddress(businessUnitRequest.getAddress());
                newBusinessUnit.setBusinessActivity(businessUnitRequest.getBusinessActivity());
                newBusinessUnit.setCity(businessUnitRequest.getCity());
                newBusinessUnit.setLocatedAt(businessUnitRequest.getLocatedAt());
                newBusinessUnit.setPermanentEmployee(businessUnitRequest.getPermanentEmployee());
                newBusinessUnit.setContractEmployee(businessUnitRequest.getContractEmployee());
                newBusinessUnit.setCreatedAt(businessUnitRequest.getCreatedAt());
                newBusinessUnit.setUpdatedAt(businessUnitRequest.getUpdatedAt());
                newBusinessUnit.setEnable(businessUnitRequest.isEnable());

                // Set the GST relationship
                newBusinessUnit.setGst(gstData.get());

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
                response.setGstId(savedBusinessUnit.getGst().getId());
                response.setGstNumber(savedBusinessUnit.getGst().getGstNumber());
//                response.setGstState(savedBusinessUnit.getGst().getGstState());

                return response;
            }
        }

        // Handle the case where the GST doesn't exist or the business unit already exists
        return null; // You might want to return an appropriate response or throw an exception here.
    }
}
