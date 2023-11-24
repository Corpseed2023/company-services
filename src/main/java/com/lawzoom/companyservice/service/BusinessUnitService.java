package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;

import java.util.List;

public interface BusinessUnitService {
    BusinessUnitResponse createBusinessUnit(BusinessUnitRequest businessUnitRequest ,Long companyId,Long teamId);

    BusinessUnitResponse updateBusinessUnit(Long companyId, Long businessUnitId, BusinessUnitRequest businessUnitRequest);
//
//    BusinessUnitResponse getBusinessUnit(Long gstId, Long businessUnitId);
//
    List<BusinessUnitResponse> getAllBusinessUnits(Long companyId);
//
//    void deleteBusinessUnit(Long gstId, Long businessUnitId);



}
