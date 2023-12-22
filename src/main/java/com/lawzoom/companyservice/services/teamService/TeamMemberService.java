package com.lawzoom.companyservice.services.teamService;

import com.lawzoom.companyservice.dto.TeamMemberDetailsResponse;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
//import com.lawzoom.companyservice.model.teamModel.Team;

import java.util.List;

public interface TeamMemberService {

    TeamMemberResponse createTeamMember(TeamMemberRequest teamMemberRequest,Long companyId,Long createdById);

    TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest teamMemberRequest);
    List<TeamMemberResponse> getAllTeamMembers(Long companyId);
    TeamMemberResponse getTeamMemberById(Long id);
    void removeTeamMember(Long memberId);

    TeamMemberDetailsResponse getTeamMemberDetailsByMail(String memberMail);

//    List<Team> getTeamWithAllTeamMember(Long companyId);
//
//    List<Team> getAllTeam();
}
