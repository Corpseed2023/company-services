package com.lawzoom.companyservice.controller.teamMemberController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
//@RequestMapping("/company/team/teamMember")
//@RequestMapping("api/v1/company/team/teamMember")
@RequestMapping("/companyServices/company/team/members")


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
    public ResponseEntity<List<TeamMemberResponse>> getAllTeamMembers(@RequestParam Long teamId) {
        List<TeamMemberResponse> allTeamMembers = teamMemberService.getAllTeamMembers(teamId);
        return new ResponseEntity<>(allTeamMembers, HttpStatus.OK);
    }


    @GetMapping("/getTeamMember")
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


//    @GetMapping("/getTeamWithTeamMember")
//    public Map<String, List<TeamMember>> getTeamWithTeamMember() {
//        Map<String, List<TeamMember>> allTeamMembers = teamMemberService.getTeamWithAllTeamMember();
//        System.out.println(" allTeamMembers "+allTeamMembers);
//        return new ResponseEntity<>(allTeamMembers, HttpStatus.CREATED);
//    }



    @GetMapping("/getTeamWithTeamMember")
    public String getTeamWithAllTeamMember(@RequestParam Long companyId) {

        List<Team> allTeamWithAllTeamMembers = teamMemberService.getTeamWithAllTeamMember(companyId);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(allTeamWithAllTeamMembers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("allTeamMembers " + json);
        return json;
    }

//    @GetMapping("/getTeamWithTeamMember")
//    public ResponseEntity<Map<String, List<TeamMember>> getTeamWithTeamMember() {
//        Map<String, List<TeamMember>> allTeamMembers = teamMemberService.getTeamWithAllTeamMember();
//        return ResponseEntity.ok(allTeamMembers);
//    }
//@GetMapping("/getAllTeam")
//public List<Team> getAllTeam() {
//    List<Team> allTeamMembers = teamMemberService.getAllTeam();
//    System.out.println("allTeamMembers " + allTeamMembers);
//    return allTeamMembers
//}



}
