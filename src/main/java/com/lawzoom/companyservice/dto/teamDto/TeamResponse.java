package com.lawzoom.companyservice.dto.teamDto;

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
@Builder
public class TeamResponse {


	private String teamName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private boolean isEnable;


}
