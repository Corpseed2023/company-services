package com.lawzoom.companyservice.model.businessActivityModel;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lawzoom.companyservice.model.teamModel.Team;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "businessIndustry")
public class BusinessIndustry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String industryName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
    @Comment(value = "1 : Active, 0 : Inactive")
    private boolean isEnable;

    @OneToMany(mappedBy = "businessIndustry", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<BusinessIndustryCategory> categories = new ArrayList<>();


}

