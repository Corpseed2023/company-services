package com.lawzoom.companyservice.dto.teamMemberDto;

import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.dto.userDto.UserResponse;
//
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeamMemberResponse {

	private Long id;
	
	private TeamResponse teamResponse;
	
	private UserResponse userResponse;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private boolean isEnable;

}
