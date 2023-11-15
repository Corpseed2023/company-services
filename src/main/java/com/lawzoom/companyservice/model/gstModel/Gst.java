package com.lawzoom.companyservice.model.gstModel;

import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.companyModel.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "gst")
public class Gst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "business_unit_id")
	private BusinessUnit businessUnit;
	
	@NotNull
	@Column(name = "gst_number",unique = true)
	private String gstNumber;
	
	@NotNull
	@Column(name = "state_jurisdiction")
	private String stateJurisdiction;
	
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;



}
