//	package com.lawzoom.companyservice.model.teamModel;
//
//	import com.fasterxml.jackson.annotation.JsonIgnore;
//	import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
//	import com.lawzoom.companyservice.model.companyModel.Company;
//	import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
//	import jakarta.persistence.*;
//	import jakarta.validation.constraints.NotNull;
//	import lombok.*;
//	import org.hibernate.annotations.Comment;
//
//	import java.util.*;
//
//	@AllArgsConstructor
//	@NoArgsConstructor
//	@Getter
//	@Setter
//	@Entity
//	@Data
//	@Table(name = "team")
//	public class Team {
//
//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		@Column(name = "id")
//		private Long id;
//
////		@ManyToOne(fetch = FetchType.LAZY)
////		@JoinColumn(name = "company_id",nullable = false)
////		@JsonIgnore
//
//		@JsonIgnore
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn(name = "company_id")
//		private Company company;
//
//		@NotNull
//		@Column(name = "team_name")
//		private String teamName;
//
//		@NotNull
//		private String teamLeadName;
//
//		@NotNull
//		private String leadDesignation;
//
//		@NotNull
//		private String teamType;
//
//		@Column(name = "created_at")
//		@Temporal(TemporalType.TIMESTAMP)
//		private Date createdAt;
//
//		@Column(name = "updated_at")
//		@Temporal(TemporalType.TIMESTAMP)
//		private Date updatedAt;
//
//
//		@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
//		@Comment(value = "1 : Active, 0 : Inactive")
//		private boolean isEnable;
//
//		private int reportingManager;
//
//		private Long superAdminId;
//
//		private Long createdBy;
//
//
//		private String uuid;
//
//		@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
//		private List<TeamMember> teamMembers = new ArrayList<>();
//
//		@ManyToMany(mappedBy = "teams")
//		private Set<BusinessUnit> businessUnits = new HashSet<>();
//
//
//	}
