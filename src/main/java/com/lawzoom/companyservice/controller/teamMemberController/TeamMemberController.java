package com.lawzoom.companyservice.controller.teamMemberController;


import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
//@RequestMapping("/company/team/teamMember")
@RequestMapping("api/v1/company/team/teamMember")

public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;


    @PostMapping("/addTeamMember")
    public ResponseEntity<TeamMemberResponse> createTeamMember(@RequestBody TeamMemberRequest teamMemberRequest, @RequestParam Long teamId) {
        TeamMemberResponse createdTeamMember = teamMemberService.createTeamMember(teamMemberRequest,teamId);
        return new ResponseEntity<>(createdTeamMember, HttpStatus.CREATED);
    }

    @PutMapping("/updateTeamMember")
    public ResponseEntity<TeamMemberResponse> updateTeamMember(
            @RequestParam Long id,
            @RequestBody TeamMemberRequest teamMemberRequest) {
        TeamMemberResponse updatedTeamMember = teamMemberService.updateTeamMember(id, teamMemberRequest);
        return new ResponseEntity<>(updatedTeamMember, HttpStatus.OK);
    }


    @GetMapping("/getAllTeamMembers")
    public ResponseEntity<List<TeamMemberResponse>> getAllTeamMembers() {
        List<TeamMemberResponse> allTeamMembers = teamMemberService.getAllTeamMembers();
        return new ResponseEntity<>(allTeamMembers, HttpStatus.OK);
    }

    @GetMapping("/getTeamMember}")
    public ResponseEntity<TeamMemberResponse> getTeamMemberById(@RequestParam Long id) {
        TeamMemberResponse teamMember = teamMemberService.getTeamMemberById(id);
        return new ResponseEntity<>(teamMember, HttpStatus.OK);
    }

    @DeleteMapping("/removeTeamMember")
    public ResponseEntity<Void>  removeTeamMember(@RequestParam Long memberId)

    {
        teamMemberService.removeTeamMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
