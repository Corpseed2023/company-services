package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.dto.AccessTypeDto;
import com.lawzoom.companyservice.model.AccessType;
import com.lawzoom.companyservice.repository.AccessTypeRepository;
import com.lawzoom.companyservice.services.AccessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessTypeServiceImpl implements AccessTypeService {

    @Autowired
    private AccessTypeRepository accessTypeRepository;

    @Override
    public AccessTypeDto createAccessType(AccessTypeDto accessTypeDto) {
        AccessType accessType = new AccessType();
        accessType.setAccessTypeName(accessTypeDto.getAccessTypeName());
        accessType.setCreatedAt(new Date());
        accessType.setUpdatedAt(new Date());
        accessType.setEnable(accessTypeDto.isEnable());

        AccessType savedAccessType = accessTypeRepository.save(accessType);

        AccessTypeDto savedAccessTypeDto = new AccessTypeDto();
        savedAccessTypeDto.setAccessTypeName(savedAccessType.getAccessTypeName());
        savedAccessTypeDto.setCreatedAt(savedAccessType.getCreatedAt());
        savedAccessTypeDto.setUpdatedAt(savedAccessType.getUpdatedAt());
        savedAccessTypeDto.setEnable(savedAccessType.isEnable());

        return savedAccessTypeDto;
    }

    @Override
    public List<AccessTypeDto> getAllAccessTypes() {
        List<AccessType> accessTypes = accessTypeRepository.findAll();

        // Map entities to DTOs
        return accessTypes.stream()
                .map(accessType -> {
                    AccessTypeDto accessTypeDto = new AccessTypeDto();
                    accessTypeDto.setAccessTypeName(accessType.getAccessTypeName());
                    accessTypeDto.setCreatedAt(accessType.getCreatedAt());
                    accessTypeDto.setUpdatedAt(accessType.getUpdatedAt());
                    accessTypeDto.setEnable(accessType.isEnable());
                    return accessTypeDto;
                })
                .collect(Collectors.toList());
    }


}
