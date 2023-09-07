package com.lawzoom.companyservice.dto.gstDto;

import com.fasterxml.jackson.annotation.JsonFormat;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GstRequest {

	private Long id;
	
	private String gstNumber;
	
	private String stateJurisdiction;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date registrationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;

	private boolean isEnable;

}
