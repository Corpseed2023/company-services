package com.lawzoom.companyservice.model.teamMemberModel;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.lawzoom.companyservice.model.teamModel.Team;
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

//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "team_id")
//	private Team team;

	@NonNull
	@NotBlank
	private String memberName;

	private String accessTypeName;

//	//basically its role table which going to save in user table
//	private Long accessTypeId;

	@NonNull
	@NotBlank
	private String memberMail;

	@NonNull
	@NotBlank
	private String memberMobile;

	private String typeOfResource;

//	@Column(name = "member_role",nullable = true)
//	private String memberRole;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

	private String password;

	private String role;

	private Long createdById;

	private Long companyId;

	public TeamMember() {

	}

//	public TeamMember(){
//
//	}

}
