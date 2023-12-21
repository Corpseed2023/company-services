package com.lawzoom.companyservice.services;

import com.lawzoom.companyservice.dto.AccessTypeDto;

import java.util.List;

public interface AccessTypeService {

    AccessTypeDto createAccessType(AccessTypeDto accessTypeDto);

    List<AccessTypeDto> getAllAccessTypes();


}
