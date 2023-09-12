	package com.lawzoom.companyservice.model.teamModel;

	import com.lawzoom.companyservice.model.companyModel.Company;
	import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
	import jakarta.persistence.*;
	import jakarta.validation.constraints.NotNull;
	import lombok.*;
	import org.hibernate.annotations.Comment;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	@Entity
	@ToString
	@Table(name = "team")
	public class Team {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Long id;

		@ManyToOne(targetEntity = Company.class)
		@JoinColumn(name = "company_id",nullable = false)
		private Company company;

		@NotNull
		@Column(name = "team_name")
		private String teamName;

		@NotNull
		private String teamLeadName;

		@NotNull
		private String leadDesignation;

		@NotNull
		private String teamType;

		@Column(name = "created_at")
		@Temporal(TemporalType.TIMESTAMP)
		private Date createdAt;

		@Column(name = "updated_at")
		@Temporal(TemporalType.TIMESTAMP)
		private Date updatedAt;

		@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
		@Comment(value = "1 : Active, 0 : Inactive")
		private boolean isEnable;

//		@OneToMany(mappedBy = "team",cascade = CascadeType.ALL,orphanRemoval = true)
//		private List<TeamMember> teamMembers=new ArrayList<>();

		@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<TeamMember> teamMembers = new ArrayList<>();

	}
