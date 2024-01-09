package com.lawzoom.companyservice.dto.companyDto;


import lombok.Data;

@Data
public class CompanyUnitActivityDetails {

    private Long   companyId;
    private String companyName;
    private Long   businessId;
    private String businessUnitAddress;
    private Long   businessActivityId;
    private String businessActivityName;

}
