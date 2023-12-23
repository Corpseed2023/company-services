package com.lawzoom.companyservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class TeamMemberDetailsResponse {

    private String memberName;
    private String companyName;
    private String accessTypeName;
    private Long companyId;


    public TeamMemberDetailsResponse(String memberName, String companyName, String accessTypeName, Long companyId) {
        this.memberName = memberName;
        this.companyName = companyName;
        this.accessTypeName = accessTypeName;
        this.companyId = companyId;
    }
}
