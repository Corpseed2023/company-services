package com.lawzoom.companyservice.dto.teamMemberDto;

import com.fasterxml.jackson.annotation.JsonFormat;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotBlank;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.*;



import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Builder
public class TeamMemberRequest {

	private Long id;

	@NonNull
	private String role;

	private Long memberId;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;

	private boolean isEnable;

}
