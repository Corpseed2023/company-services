package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.repository.CompanyRepository;
import com.lawzoom.companyservice.repository.TeamRepository;
import com.lawzoom.companyservice.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public TeamResponse createTeam(TeamRequest teamRequest, Long companyId) {

        Optional < Company > companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            try {
                if (teamRequest == null) {
                    throw new IllegalArgumentException("Invalid TeamRequest");
                }
                Team team = new Team();

                team.setTeamName(teamRequest.getTeamName());
                team.setCompany(company);
                team = teamRepository.save(team);

                TeamResponse teamResponse = new TeamResponse();

                teamResponse.setTeamName(team.getTeamName());

                return teamResponse;

            } catch (Exception e) {
                e.printStackTrace();

                throw new RuntimeException("Failed to create team", e);
            }
        }

        return null;
    }

    @Override
    public List<TeamResponse> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamResponse> teamResponses = new ArrayList<>();

        for (Team team : teams) {
            TeamResponse teamResponse = new TeamResponse();
            teamResponse.setTeamName(team.getTeamName());
            teamResponse.setCreatedAt(team.getCreatedAt());
            teamResponse.setUpdatedAt(team.getUpdatedAt());
            teamResponse.setEnable(team.isEnable());

            teamResponses.add(teamResponse);
        }

        return teamResponses;
    }

    @Override
    public TeamResponse getTeamById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            TeamResponse teamResponse = new TeamResponse();
            teamResponse.setTeamName(team.getTeamName());
            teamResponse.setCreatedAt(team.getCreatedAt());
            teamResponse.setUpdatedAt(team.getUpdatedAt());
            teamResponse.setEnable(team.isEnable());
            return teamResponse;
        } else {
            throw new EntityNotFoundException("Team not found with ID: " + teamId);
        }
    }

    @Override
    public TeamResponse updateTeam(Long teamId, TeamRequest teamRequest) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();

            // Update the fields from the TeamRequest
            team.setTeamName(teamRequest.getTeamName());
            team.setCreatedAt(teamRequest.getCreatedAt());
            team.setUpdatedAt(teamRequest.getUpdatedAt());
            team.setEnable(teamRequest.isEnable());

            // Save the updated team
            Team updatedTeam = teamRepository.save(team);

            // Create a TeamResponse manually
            TeamResponse teamResponse = new TeamResponse();
            teamResponse.setTeamName(updatedTeam.getTeamName());
            teamResponse.setCreatedAt(updatedTeam.getCreatedAt());
            teamResponse.setUpdatedAt(updatedTeam.getUpdatedAt());
            teamResponse.setEnable(updatedTeam.isEnable());

            return teamResponse;
        } else {
            throw new EntityNotFoundException("Team not found with ID: " + teamId);
        }
    }

    @Override
    public void deleteTeamById(Long teamId) {
        Optional<Team> teamData = teamRepository.findById(teamId);
        if (teamData.isPresent()) {
            Team team = teamData.get();
            teamRepository.delete(team);
        } else {
            throw new EntityNotFoundException("Team not found ");
        }
    }
}


