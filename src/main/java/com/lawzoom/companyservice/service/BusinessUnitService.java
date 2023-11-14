package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;

import java.util.List;

public interface BusinessUnitService {
    BusinessUnitResponse createBusinessUnit(BusinessUnitRequest businessUnitRequest ,Long companyId);

    BusinessUnitResponse updateBusinessUnit(Long gstId, Long businessUnitId, BusinessUnitRequest businessUnitRequest);

    BusinessUnitResponse getBusinessUnit(Long gstId, Long businessUnitId);

    List<BusinessUnitResponse> getAllBusinessUnits(Long gstId);

    void deleteBusinessUnit(Long gstId, Long businessUnitId);



}
