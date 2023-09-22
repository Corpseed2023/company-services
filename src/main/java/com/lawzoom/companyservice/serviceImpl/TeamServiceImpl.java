package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.repository.CompanyRepository;
import com.lawzoom.companyservice.repository.TeamRepository;
import com.lawzoom.companyservice.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        Optional <Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            try {
                if (teamRequest == null) {
                    throw new IllegalArgumentException("Invalid TeamRequest");
                }
                Team team = new Team();

                team.setTeamName(teamRequest.getTeamName());
                team.setCompany(company);
                team.setTeamLeadName(teamRequest.getTeamLeadName());
                team.setLeadDesignation(teamRequest.getLeadDesignation());
                team.setTeamType(teamRequest.getTeamType());
                team.setEnable(teamRequest.isEnable());
                team.setCreatedAt(new Date());
                team.setUpdatedAt(new Date());
                team = teamRepository.save(team);

                TeamResponse teamResponse = new TeamResponse();

                teamResponse.setTeamName(team.getTeamName());
                teamResponse.setTeamType(team.getTeamType());
                teamResponse.setTeamLeadName(team.getTeamLeadName());
                teamResponse.setLeadDesignation(team.getLeadDesignation());
                teamResponse.setCreatedAt(team.getCreatedAt());
                teamResponse.setUpdatedAt(team.getUpdatedAt());

                return teamResponse;

            } catch (Exception e) {
                e.printStackTrace();

                throw new RuntimeException("Failed to create team", e);
            }
        }

        else {
            throw new IllegalArgumentException("Company not available with ID: " + companyId);
        }
    }

    public List<TeamResponse> getAllTeams(Long companyId) {
        List<Team> teams = teamRepository.findAllByCompanyId(companyId);
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


