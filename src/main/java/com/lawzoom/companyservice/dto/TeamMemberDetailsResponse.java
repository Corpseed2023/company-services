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


    public TeamMemberDetailsResponse(String memberName, String companyName, String accessTypeName) {
        this.memberName = memberName;
        this.companyName = companyName;
        this.accessTypeName = accessTypeName;
    }


}
