package com.lawzoom.companyservice.feignClient;

import com.lawzoom.companyservice.dto.userDto.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AUTHENTICATION-SERVICE", url = "http://localhost:8081")
public interface AuthenticationFeignClient {

    @PostMapping("/api/auth/user/createTeamMember")
    void createTeamMemberUsers(@RequestBody UserRequest userRequest);

    @PutMapping("/api/auth/user/updateIsAssociated")
    void updateIsAssociated(@RequestParam Long userId, @RequestParam boolean isAssociated);


}
