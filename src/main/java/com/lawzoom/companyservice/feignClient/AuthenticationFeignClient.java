package com.lawzoom.companyservice.feignClient;

import com.lawzoom.companyservice.dto.userDto.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AUTHENTICATION-SERVICE", url = "http://localhost:8081")
public interface AuthenticationFeignClient {

    @PostMapping("/api/auth/user/createTeamMember")
    void createTeamMemberUsers(@RequestBody UserRequest userRequest);
}
