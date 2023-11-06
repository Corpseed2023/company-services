package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;

import java.util.List;

public interface TeamMemberService {

    TeamMemberResponse createTeamMember(TeamMemberRequest teamMemberRequest,Long teamId);

    TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest teamMemberRequest);
    List<TeamMemberResponse> getAllTeamMembers(Long teamId);
    TeamMemberResponse getTeamMemberById(Long id);
    void removeTeamMember(Long memberId);

    List<TeamMemberResponse> getTeamWithAllTeamMember();
}
