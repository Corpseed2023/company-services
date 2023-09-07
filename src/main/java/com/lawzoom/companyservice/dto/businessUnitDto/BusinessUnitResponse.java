package com.lawzoom.companyservice.dto.businessUnitDto;

import lombok.*;


import java.util.Date;

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

	private boolean isEnable;

	private Long gstId;

	private String gstNumber;

	private String gstState;

}
