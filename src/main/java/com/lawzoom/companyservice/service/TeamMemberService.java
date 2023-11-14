package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;

import java.util.List;
import java.util.Map;

public interface TeamMemberService {

    TeamMemberResponse createTeamMember(TeamMemberRequest teamMemberRequest,Long teamId);

    TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest teamMemberRequest);
    List<TeamMemberResponse> getAllTeamMembers(Long teamId);
    TeamMemberResponse getTeamMemberById(Long id);
    void removeTeamMember(Long memberId);

    List<Team> getTeamWithAllTeamMember(Long companyId);

    List<Team> getAllTeam();
}
