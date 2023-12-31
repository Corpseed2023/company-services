package com.lawzoom.companyservice.dto.teamMemberDto;

import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.dto.userDto.UserResponse;
//
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

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

	private String accessType;

	@NonNull
	@NotBlank
	private String memberMail;

	@NonNull
	@NotBlank
	private String memberMobile;

	private String typeOfResource;
	
	private TeamResponse teamResponse;
	
	private UserResponse userResponse;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private boolean isEnable;

}
