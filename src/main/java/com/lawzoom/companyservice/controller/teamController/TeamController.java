//package com.lawzoom.companyservice.controller.teamController;
//
//
//import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
//import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
//import com.lawzoom.companyservice.services.teamService.TeamService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@CrossOrigin
//@RestController
////@RequestMapping("/company/team")
////@RequestMapping("/api/v1/company/team")
//@RequestMapping("/companyServices/company/team")
//public class TeamController {
//
//    @Autowired
//    private TeamService teamService;
//
//    @PostMapping("/addTeam")
//    public ResponseEntity createTeam(@Valid @RequestBody TeamRequest teamRequest, @RequestParam Long companyId,
//                                     @RequestParam Long createdById){
//
//        TeamResponse teamResponse = teamService.createTeam(teamRequest,companyId,createdById);
//        return new ResponseEntity<>(teamResponse, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/allTeams")
//    public ResponseEntity<List<TeamResponse>> getAllTeams(@RequestParam Long companyId) {
//        List<TeamResponse> teams = teamService.getAllTeams(companyId);
//        return new ResponseEntity<>(teams, HttpStatus.OK);
//    }
//
//    @GetMapping("/team")
//    public ResponseEntity<TeamResponse> getTeamById(@RequestParam Long teamId) {
//        TeamResponse teamResponse = teamService.getTeamById(teamId);
//        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
//    }
//
//    @PutMapping("/updateTeam")
//    public ResponseEntity<TeamResponse> updateTeam( @RequestParam Long teamId,
//                                                    @RequestBody TeamRequest teamRequest
//    ) {
//        TeamResponse updatedTeam = teamService.updateTeam(teamId, teamRequest);
//        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteTeam")
//    public ResponseEntity<Void> deleteTeamById(@RequestParam Long teamId) {
//        teamService.deleteTeamById(teamId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
