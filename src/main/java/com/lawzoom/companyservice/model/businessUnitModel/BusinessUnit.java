package com.lawzoom.companyservice.model.businessUnitModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.gstModel.Gst;

//import javax.persistence.*;
//import javax.validation.constraints.NotNull;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;


import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "business_unit")
public class BusinessUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@NotNull
	@Column(name = "business_activity")
	private String businessActivity;
	
	@NotNull
	private String city;
	
	@Column(name = "located_at")
	private String locatedAt;
	
	@Column(name = "permanent_employee")
	private int permanentEmployee;

	@Column(name = "contract_employee")
	private int contractEmployee;
	
	@NotNull
	@Column(name = "address",columnDefinition = "TINYTEXT")
	private String address;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_registration")
	private Date dateRegistration;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

	private String gstNumber;

	private String states;




//	@OneToMany(mappedBy = "businessUnit", cascade = CascadeType.ALL)
//	private List<Gst> gstList;

//	@OneToMany(mappedBy = "businessUnit", cascade = CascadeType.ALL)
//	private List<Gst> gstList;



}
