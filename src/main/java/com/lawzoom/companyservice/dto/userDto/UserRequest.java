package com.lawzoom.companyservice.dto.userDto;


import com.lawzoom.companyservice.model.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Size(min = 10,max = 13,message = "Mobile length should be 10 to 13 digits..")

    private String mobile;

    private String otp;

    @Size(min = 6,message = "Password length should be minimum 6.")

    private String password;

    private String designation;

    private String resourceType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Comment(value = "1 : Active, 0 : Inactive")
    private boolean isEnable;

    private Set<Roles> roles;

    private boolean isAssociated;

    private Long company_id;


    @Override
    public String toString() {
        return "UserRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", otp='" + otp + '\'' +
                ", password='" + password + '\'' +
                ", designation='" + designation + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isEnable=" + isEnable +
                ", roles=" + roles +
                ", isAssociated=" + isAssociated +
                ", company_id=" + company_id +
                '}';
    }
}
