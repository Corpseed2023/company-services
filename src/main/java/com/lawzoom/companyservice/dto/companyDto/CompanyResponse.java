package com.lawzoom.companyservice.dto.companyDto;

import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
//import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.model.companyModel.CompanyType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    private Long companyId;

    private Long userId;
//
//    private Long userId;

//    private CompanyType companyType;

    private String companyType;

    private String companyName;

    private String firstName;

    private String lastName;

    private String businessActivityEmail;

    private String designation;

    private String companyState;

    private String companyCity;

    private String companyRegistrationNumber;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date companyRegistrationDate;

    private String companyCINNumber;

    private String companyRemarks;

    private String companyPinCode;

    private String companyAddress;

    private long companyTurnover;

    private String locatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean isEnable;

    private String businessActivity;

    private int permanentEmployee;

    private int contractEmployee;

//    private List<GstResponse> gstResponseList;

//    private int businessUnits;

    private String gstNumber;

    private String operationUnitAddress;

//    private List<Team> teamsList;

//    private List<BusinessUnit> businessUnits;

}
