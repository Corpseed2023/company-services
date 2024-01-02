package com.lawzoom.companyservice.serviceImpl.teamLogic;

import com.lawzoom.companyservice.controller.PasswordController;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberDetailsResponse;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.dto.userDto.UserRequest;
import com.lawzoom.companyservice.feignClient.AuthenticationFeignClient;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.repository.companyRepo.CompanyRepository;
import com.lawzoom.companyservice.repository.team.TeamMemberRepository;
import com.lawzoom.companyservice.config.EmailService;
import com.lawzoom.companyservice.services.teamService.TeamMemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private PasswordController passwordController;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmailService emailService;

    private JavaMailSender javaMailSender;

    @Autowired
    private AuthenticationFeignClient authenticationFeignClient;

    @Override
    public TeamMemberResponse createTeamMember(TeamMemberRequest teamMemberRequest, Long companyId, Long createdById) {

//        UserRequest userData = authenticationFeignClient.getUserId(createdById);
//        if (userData == null) {
//            throw new IllegalArgumentException("User not found with ID: " + createdById);
//        }

        Optional<Company> companySavedData = companyRepository.findById(companyId);

//        Optional<TeamMember> teamMemberData = teamMemberRepository.findById(teamMemberRequest.getReportingManagerId());


        if (companySavedData.isPresent()) {
            Company companyData = companySavedData.get();

            companyData.getUserId();

            try {
                if (teamMemberRequest == null) {
                    throw new NullPointerException("Please Enter Team Member Data");
                }

                String randomPassword = passwordController.generateRandomPassword();

                TeamMember teamMember = new TeamMember();

                teamMember.setMemberName(teamMemberRequest.getMemberName());
                teamMember.setMemberMail(teamMemberRequest.getMemberMail());
                teamMember.setMemberMobile(teamMemberRequest.getMemberMobile());
                teamMember.setTypeOfResource(teamMemberRequest.getTypeOfResource());
                teamMember.setCreatedAt(new Date());
                teamMember.setUpdatedAt(new Date());
                teamMember.setEnable(teamMemberRequest.isEnable());
                teamMember.setAccessTypeName(teamMemberRequest.getAccessTypeName());
                teamMember.setCompany(companyData);
                teamMember.setSuperAdminId(companyData.getUserId());
                teamMember.setReportingManagerId(companyData.getUserId());




                System.out.println("Got Hit");


                teamMember = teamMemberRepository.save(teamMember);

                TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
                teamMemberResponse.setMemberMail(teamMember.getMemberMail());
                teamMemberResponse.setMemberName(teamMember.getMemberName());
                teamMemberResponse.setCreatedAt(teamMember.getCreatedAt());
                teamMemberResponse.setUpdatedAt(teamMember.getUpdatedAt());
                teamMemberResponse.setEnable(teamMember.isEnable());
                teamMemberResponse.setMemberMobile(teamMember.getMemberMobile());
                teamMemberResponse.setTypeOfResource(teamMember.getTypeOfResource());
                teamMemberResponse.setAccessTypeName(teamMember.getAccessTypeName());

                teamMemberResponse.setReportingManagerId(teamMember.getId());

                UserRequest userRequest = new UserRequest();
//
                userRequest.setFirstName(teamMemberResponse.getMemberName());
                userRequest.setEmail(teamMemberResponse.getMemberMail());
                userRequest.setSubscribed(false);


////                AccessType r = new AccessType();
////                r.setId(teamMemberResponse.getAccessTypeId());
////                r.setAccessTypeName(teamMemberResponse.getAccessType());
////                Set<AccessType> s = new HashSet<>();
////                s.add(r);
////                userRequest.setRoles(s);
                userRequest.setPassword(randomPassword);
////                userRequest.setDesignation(teamMemberResponse.get);
//                userRequest.setResourceType(teamMemberResponse.getTypeOfResource());
////                userRequest.setRoles(teamMemberResponse.getAccessType());
//                userRequest.setCompanyId(1L);
//                System.out.println(userRequest);
//
                authenticationFeignClient.createTeamMemberUsers(userRequest);
////                sendInvitationEmail(teamMemberRequest);


                System.out.println("Got Wokring Authentication Hit");

                return teamMemberResponse;

            } catch (Exception e) {
                e.printStackTrace();

                throw new RuntimeException("Failed to create team members");
            }
        } else {
            throw new IllegalArgumentException("Company not found with ID: " + companyId);
        }
    }

    @Override
    public TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest teamMemberRequest) {
        Optional<TeamMember> optionalTeamMember = teamMemberRepository.findById(id);

        if (!optionalTeamMember.isPresent()) {

            String errorMessage = "TeamMember with ID " + id + " not found";
            throw new EntityNotFoundException(errorMessage);
        }

        TeamMember teamMember = optionalTeamMember.get();

        teamMember.setMemberName(teamMemberRequest.getMemberName());
        teamMember.setMemberMail(teamMemberRequest.getMemberMail());
        teamMember.setMemberMobile(teamMemberRequest.getMemberMobile());
        teamMember.setCreatedAt(new Date());
        teamMember.setUpdatedAt(new Date());
        teamMember.setEnable(teamMemberRequest.isEnable());
        teamMember.setTypeOfResource(teamMemberRequest.getTypeOfResource());


        teamMember = teamMemberRepository.save(teamMember);
//        System.out.println("kaushal");

        TeamMemberResponse teamMemberResponse = new TeamMemberResponse();

        teamMemberResponse.setCreatedAt(teamMember.getCreatedAt());
        teamMemberResponse.setUpdatedAt(teamMember.getUpdatedAt());
        teamMemberResponse.setEnable(teamMember.isEnable());
        teamMemberResponse.setMemberMail(teamMember.getMemberMail());
        teamMemberResponse.setMemberMobile(teamMember.getMemberMobile());
//        teamMemberResponse.setAccessType(teamMember.getAccessType());
        teamMemberResponse.setMemberName(teamMember.getMemberName());


        System.out.println("kaushal");

        return teamMemberResponse;
    }


    @Override
    public List<TeamMemberResponse> getAllTeamMembers(Long companyId) {
        List<TeamMember> teamMembers = teamMemberRepository.findAllByCompanyId(companyId);
        List<TeamMemberResponse> teamMemberResponses = new ArrayList<>();
        System.out.println("kaushal");


        for (TeamMember teamMember : teamMembers) {
            TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
            System.out.println("kaushal");

            teamMemberResponse.setCreatedAt(teamMember.getCreatedAt());
            teamMemberResponse.setUpdatedAt(teamMember.getUpdatedAt());
            teamMemberResponse.setEnable(teamMember.isEnable());
            teamMemberResponse.setMemberName(teamMember.getMemberName());
            teamMemberResponse.setMemberMobile(teamMember.getMemberMobile());
            teamMemberResponse.setMemberMail(teamMember.getMemberMail());
            teamMemberResponse.setEnable(teamMember.isEnable());
//            teamMemberResponse.setCompanyId(teamMember.getCompanyId());
            teamMemberResponse.setTypeOfResource(teamMember.getTypeOfResource());
//            teamMemberResponse.setAccessType(teamMember.getAccessType());


            teamMemberResponses.add(teamMemberResponse);
        }

        return teamMemberResponses;
    }

    @Override
    public TeamMemberResponse getTeamMemberById(Long id) {
        Optional<TeamMember> optionalTeamMember = teamMemberRepository.findById(id);

        if (!optionalTeamMember.isPresent()) {
            System.out.println("kaushal");
            throw new EntityNotFoundException("TeamMember with ID " + id + " not found");
        }

        TeamMember teamMember = optionalTeamMember.get();
        TeamMemberResponse teamMemberResponse = new TeamMemberResponse();

        teamMemberResponse.setMemberName(teamMember.getMemberName());
        teamMemberResponse.setMemberMobile(teamMember.getMemberMobile());
        teamMemberResponse.setMemberMail(teamMember.getMemberMail());

        System.out.println("kaushal");
        teamMemberResponse.setCreatedAt(teamMember.getCreatedAt());
        teamMemberResponse.setUpdatedAt(teamMember.getUpdatedAt());
        teamMemberResponse.setEnable(teamMember.isEnable());


        return teamMemberResponse;
    }

    @Override
    public void removeTeamMember(Long memberId) {

        Optional<TeamMember> memberData = teamMemberRepository.findById(memberId);

        if (memberData.isPresent()) {
            TeamMember membertoDelete = memberData.get();

            teamMemberRepository.delete(membertoDelete);

        } else {
            throw new EntityNotFoundException("Member Not present in database");
        }

    }




    public TeamMemberDetailsResponse getTeamMemberDetailsByMail(String memberMail) {
        List<TeamMember> teamMembers = teamMemberRepository.findByMemberMail(memberMail);

        if (!teamMembers.isEmpty()) {
            // Handle non-unique results, for example, you can choose the first result
            TeamMember teamMember = teamMembers.get(0);
            Company company = companyRepository.findById(teamMember.getCompany().getId()).orElse(null);

            if (company != null) {
                return new TeamMemberDetailsResponse(
                        teamMember.getMemberName(),
                        company.getCompanyName(),
                        teamMember.getAccessTypeName(),
                        company.getId()
                        // Initialize other fields as needed
                );
            }
        }
        return null;
    }

    @Override
    public List<TeamMemberResponse> getTeamMembersWithIdAndTeamName(Long companyId) {
        return null;
    }



}






