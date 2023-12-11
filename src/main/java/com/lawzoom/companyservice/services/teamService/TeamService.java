package com.lawzoom.companyservice.services.teamService;


import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamResponse;

import java.util.List;


public interface TeamService {
    TeamResponse createTeam(TeamRequest teamRequest,Long companyId);

    TeamResponse updateTeam(Long teamId, TeamRequest teamRequest);

    List<TeamResponse> getAllTeams(Long companyId);

    TeamResponse getTeamById(Long teamId);

    void deleteTeamById(Long teamId);
}
