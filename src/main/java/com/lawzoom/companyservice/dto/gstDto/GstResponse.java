package com.lawzoom.companyservice.dto.gstDto;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GstResponse {

	private Long id;
	
	private String gstNumber;
	
	private String stateJurisdiction;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private boolean isEnable;

//	private List<BusinessUnitResponse> businessUnitResponseList;
//
//	private Long companyId;
//
//	private String companyName;

	private BusinessUnitResponse businessUnitResponse;


}
