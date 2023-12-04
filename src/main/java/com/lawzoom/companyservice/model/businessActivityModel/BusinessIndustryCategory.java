//package com.lawzoom.companyservice.model.businessActivityModel;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Comment;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "businessIndustryCategory")
//public class BusinessIndustryCategory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String industryCategoryName;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_at")
//    private Date updatedAt;
//
//    @Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
//    @Comment(value = "1 : Active, 0 : Inactive")
//    private boolean isEnable;
//
//    @OneToMany(mappedBy = "businessIndustryCategory", cascade = CascadeType.ALL)
//    private List<BusinessActivity> categories = new ArrayList<>();
//
//
//}
