package com.lawzoom.companyservice.dto.teamMemberDto;

import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Builder
public class TeamMemberResponse {


	@NonNull
	@NotBlank
	private String memberName;

	private String accessTypeName;

	@NonNull
	@NotBlank
	private String memberMail;

	@NonNull
	@NotBlank
	private String memberMobile;

	private String typeOfResource;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private boolean isEnable;

	private Long reportingManagerId;

	private Long subscriptionId;

	private Long createdBy;

	private Long superAdminId;


}
