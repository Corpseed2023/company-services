package com.lawzoom.companyservice.dto.companyDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawzoom.companyservice.model.teamModel.Team;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;


import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompanyRequest {

//    private Long companyId;

//    private Long userId;

    private String companyType;

    private String firstName;

    private String lastName;

    private String businessActivityEmail;

    private String designation;

    private String companyName;

    private String companyState;

    private String companyCity;

    private String companyRegistrationNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date companyRegistrationDate;

    private String companyCINNumber;

    private String companyRemarks;

    private String companyPinCode;

    private String companyAddress;

    private long companyTurnover;

    private String locatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedAt;

    private boolean isEnable;

    private String businessActivity;

    private int permanentEmployee;

    private int contractEmployee;

//    private Team team;

    private String gstNumber;

    private String operationUnitAddress;


}
