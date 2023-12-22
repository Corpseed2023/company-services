package com.lawzoom.companyservice.dto.businessUnitDto;

//import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
//import com.lawzoom.companyservice.model.teamModel.Team;
import jakarta.persistence.Column;
import lombok.*;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessUnitResponse {

	private Long id;

	private Long companyId;

	private String businessActivity;

	private String city;

	private String locatedAt;

	private int permanentEmployee;

	private int contractEmployee;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_registration")
	private Date dateRegistration;

	private boolean isEnable;

//	private List<Long> teamIds;

//	private List<TeamResponse> teams;

	private String gstNumber;

	private String states;



}
