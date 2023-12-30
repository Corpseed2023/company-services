package com.lawzoom.companyservice.model.teamMemberModel;


import com.lawzoom.companyservice.model.companyModel.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Comment;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "team_member")
public class TeamMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

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

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

//	private String password;

//	private String role;

//	private Long createdById;

	private Long subscriptionId;

//	private Long superAdminId;

//	private Long companyId;

//	@ManyToOne
//	@JoinColumn(name = "reporting_manager_id")
//	private TeamMember teamMember;

	@ManyToOne
	@JoinColumn(name = "company_id") // Adjust the column name as needed
	private Company company;

	private Long reportingManagerId;

//	@Column(name = "reporting_manager_name")
//	private String reportingMangerName;

	public TeamMember() {

	}

}
