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

    //    @NotNull
    private String firstName;

    private String lastName;
    //
//    @NotNull
//    @NotBlank
    private String email;

    @Size(min = 10,max = 13,message = "Mobile length should be 10 to 13 digits..")
//    @NotBlank
//    @NotEmpty
//    @NotNull
    private String mobile;

    //    @NotNull
//    @NotBlank
//    @NotEmpty
    private String otp;

    @Size(min = 6,message = "Password length should be minimum 6.")
//    @NotNull(message = "Password You Can't be null !!")
//    @NotBlank(message = "Please enter user password !!")
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


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }


}
