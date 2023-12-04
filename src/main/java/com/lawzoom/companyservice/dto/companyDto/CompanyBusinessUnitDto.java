package com.lawzoom.companyservice.dto.companyDto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyBusinessUnitDto {
    private Long companyId;
    private String companyName;
    private Long businessUnitId;
    private String businessUnit;
    private String address;
    private List<TotalComplianceDto> totalCompliance;
}