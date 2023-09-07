package com.lawzoom.companyservice.service;


import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.model.teamModel.Team;

import java.util.List;


public interface TeamService {
    TeamResponse createTeam(TeamRequest teamRequest,Long companyId);

    TeamResponse updateTeam(Long teamId, TeamRequest teamRequest);

    List<TeamResponse> getAllTeams();

    TeamResponse getTeamById(Long teamId);

    void deleteTeamById(Long teamId);
}
