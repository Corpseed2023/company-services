package com.lawzoom.companyservice.services.businessService;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;

import java.util.List;

public interface BusinessUnitService {
    BusinessUnitResponse createBusinessUnit(BusinessUnitRequest businessUnitRequest ,Long companyId);
    BusinessUnitResponse updateBusinessUnit(Long companyId, Long businessUnitId, BusinessUnitRequest businessUnitRequest);
    List<BusinessUnitResponse> getAllBusinessUnits(Long companyId);
    List<BusinessUnitResponse> getAllBusinessUnitsWithAllCompany();

}
