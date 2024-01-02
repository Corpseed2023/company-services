package com.lawzoom.companyservice.dto.teamMemberDto;

import com.fasterxml.jackson.annotation.JsonFormat;


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
public class TeamMemberRequest {


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
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;

	private boolean isEnable;

	private Long reportingManagerId;

	private Long subscriptionId;


}
