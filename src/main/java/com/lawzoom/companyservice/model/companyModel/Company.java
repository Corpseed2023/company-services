package com.lawzoom.companyservice.model.companyModel;

import com.lawzoom.companyservice.model.businessActivityModel.BusinessActivity;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.region.City;
import com.lawzoom.companyservice.model.region.States;
//import com.lawzoom.companyservice.model.teamModel.Team;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "business_act_email")
	@NotNull
	private String businessActivityEmail;

	@Column(name = "designation")
	@NotNull
	private String designation;
	
//	@Column(name = "company_type")
//	private String companyType;

	@ManyToOne
	@JoinColumn(name = "companyType_id")
	private CompanyType companyType;


	@Column(name = "companyName")
	private String companyName;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private States state;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@Column(name = "registration_number")
	private String registrationNumber;

	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@Column(name = "cin_number")
	private String cinNumber;

	@Column(columnDefinition = "text",name="remarks")
	private String remarks;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name="address",columnDefinition = "TINYTEXT")
	private String address;

	@Column(name = "turnover")
	private long turnover;

	@Column(name = "located_at")
	private String locatedAt;

	@ManyToOne
	@JoinColumn(name = "business_activity_id") // Adjust the column name as needed
	private BusinessActivity businessActivity;


	@Column(name = "permanent_employee")
	private int permanentEmployee;

	@Column(name = "contract_employee")
	private int contractEmployee;

	@Column(name="gst_no")
	private String gstNumber;

	private String operationUnitAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

//	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
//	private List<Team> teams;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<BusinessUnit> businessUnits;



}
