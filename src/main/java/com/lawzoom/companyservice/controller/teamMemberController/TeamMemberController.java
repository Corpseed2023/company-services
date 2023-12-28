package com.lawzoom.companyservice.controller.teamMemberController;



import com.lawzoom.companyservice.dto.TeamMemberDetailsResponse;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.services.teamService.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
//@RequestMapping("/company/team/teamMember")
//@RequestMapping("api/v1/company/team/teamMember")
@RequestMapping("/companyServices/company/team/members")


public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping("/addTeamMember")
    public ResponseEntity<TeamMemberResponse> createTeamMember(@RequestBody TeamMemberRequest teamMemberRequest,
                                                               @RequestParam Long companyId,@RequestParam Long createdById) {
        TeamMemberResponse createdTeamMember = teamMemberService.createTeamMember(teamMemberRequest,companyId,createdById);
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
    public ResponseEntity<List<TeamMemberResponse>> getAllTeamMembers(@RequestParam Long companyId) {
        List<TeamMemberResponse> allTeamMembers = teamMemberService.getAllTeamMembers(companyId);
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



//    @GetMapping("/getTeamWithTeamMember")
//    public String getTeamWithAllTeamMember(@RequestParam Long companyId) {
//
//        List<Team> allTeamWithAllTeamMembers = teamMemberService.getTeamWithAllTeamMember(companyId);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = null;
//        try {
//            json = objectMapper.writeValueAsString(allTeamWithAllTeamMembers);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("allTeamMembers " + json);
//        return json;
//    }

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

    @GetMapping("/memberCompanyDetails")
    public ResponseEntity<TeamMemberDetailsResponse> getTeamMemberDetails(@RequestParam String memberMail) {
        TeamMemberDetailsResponse teamMemberDetailsResponse = teamMemberService.getTeamMemberDetailsByMail(memberMail);
        if (teamMemberDetailsResponse != null) {
            return new ResponseEntity<>(teamMemberDetailsResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
