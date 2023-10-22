package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;

public interface BusinessUnitService {
    BusinessUnitResponse createBusinessUnit(BusinessUnitRequest businessUnitRequest ,Long gstId);
}
