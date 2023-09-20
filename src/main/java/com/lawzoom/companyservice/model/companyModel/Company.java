package com.lawzoom.companyservice.model.companyModel;

import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.model.teamModel.Team;

//import javax.persistence.*;
//import javax.validation.constraints.NotNull;

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
@Builder
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

//	First Page of Figma entries

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

//	End of Figma first page Entries

//	Start of second page entries

	@Column(name = "company_type")
	private String companyType;
	
	@Column(name = "companyName")
	private String companyName;
		
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "registration_number")
	private String registrationNumber;
	
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@Column(name = "cin_number")
	private String cinNumber;

	@Column(columnDefinition = "TEXT",name="remarks")	
	private String remarks;
	
	@Column(name = "pin_code")
	private String pinCode;
	
	@Column(name="address",columnDefinition = "TINYTEXT")
	private String address;
	
	@Column(name = "turnover")
	private long turnover;
	
	@Column(name = "located_at")
	private String locatedAt;
	
	@Column(name = "business_activity")
	private String businessActivity;
	
	@Column(name = "permanent_employee")
	private int permanentEmployee;
	
	@Column(name = "contract_employee")
	private int contractEmployee;

	@Column(name="gst_no")
	private String gstNumber;

	@Column(name = "operation_unit_address")
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

	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Team> teams;

	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Gst> gstList;

	@Override
	public String toString() {
		return "Company{" +
				"id=" + id +
				", userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", businessActivityEmail='" + businessActivityEmail + '\'' +
				", designation='" + designation + '\'' +
				", companyType='" + companyType + '\'' +
				", companyName='" + companyName + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", registrationNumber='" + registrationNumber + '\'' +
				", registrationDate=" + registrationDate +
				", cinNumber='" + cinNumber + '\'' +
				", remarks='" + remarks + '\'' +
				", pinCode='" + pinCode + '\'' +
				", address='" + address + '\'' +
				", turnover=" + turnover +
				", locatedAt='" + locatedAt + '\'' +
				", businessActivity='" + businessActivity + '\'' +
				", permanentEmployee=" + permanentEmployee +
				", contractEmployee=" + contractEmployee +
				", gstNumber='" + gstNumber + '\'' +
				", operationUnitAddress='" + operationUnitAddress + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", isEnable=" + isEnable +
				", teams=" + teams +
				", gstList=" + gstList +
				'}';
	}
}
