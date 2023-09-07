package com.lawzoom.companyservice.model.teamMemberModel;

import com.lawzoom.companyservice.model.teamModel.Team;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import java.util.Date;
//import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "team_member")
public class TeamMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(targetEntity = Team.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id",nullable = false)
	private Team team;
	
	@Column(name = "team_member_id",nullable = false)
	private Long memberId;

	@Column(name = "member_role",nullable = false)
	private String memberRole;

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
