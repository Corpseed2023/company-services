package com.lawzoom.companyservice.model.businessActivityModel;

import com.lawzoom.companyservice.model.companyModel.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "businessActivity")
public class BusinessActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "businessActivity", cascade = CascadeType.ALL)
    private List<Company> companies;

    private String businessActivityName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
    @Comment(value = "1 : Active, 0 : Inactive")
    private boolean isEnable;

    @ManyToOne
    @JoinColumn(name="businessIndustryCategory_id")
    private BusinessIndustryCategory businessIndustryCategory;
}
