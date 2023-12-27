package com.lawzoom.companyservice.dto.teamMemberDto;

import com.fasterxml.jackson.annotation.JsonFormat;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotBlank;

import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;


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

	private TeamMember reportingManager;




}
