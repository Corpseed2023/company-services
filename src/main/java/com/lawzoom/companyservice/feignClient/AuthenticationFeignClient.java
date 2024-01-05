package com.lawzoom.companyservice.feignClient;

import com.lawzoom.companyservice.dto.userDto.UserRequest;
import com.lawzoom.companyservice.dto.userDto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHENTICATION-SERVICE", url = "http://localhost:8081")
@Service
public interface AuthenticationFeignClient {

    @PostMapping("/api/auth/user/createTeamMember")
    UserResponse createTeamMemberUsers(@RequestBody UserRequest userRequest);

//    @PutMapping("/api/auth/user/updateIsAssociated")
//    void updateIsAssociated(@RequestParam Long userId, @RequestParam boolean isAssociated);

    @GetMapping("/api/auth/user/getUserId")
    UserRequest getUserId(@RequestParam Long userId);
}
