package com.lawzoom.companyservice.serviceImpl.teamLogic;

import com.lawzoom.companyservice.controller.PasswordController;
import com.lawzoom.companyservice.dto.TeamMemberDetailsResponse;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.dto.userDto.UserRequest;
import com.lawzoom.companyservice.feignClient.AuthenticationFeignClient;
import com.lawzoom.companyservice.model.AccessType;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
//import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.repository.companyRepo.CompanyRepository;
import com.lawzoom.companyservice.repository.team.TeamMemberRepository;
//import com.lawzoom.companyservice.repository.team.TeamRepository;
import com.lawzoom.companyservice.config.EmailService;
import com.lawzoom.companyservice.services.teamService.TeamMemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;
//
//    @Autowired
//    private TeamRepository teamRepository;

    @Autowired
    private PasswordController passwordController;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmailService emailService;

    private JavaMailSender javaMailSender;

    @Autowired
    private AuthenticationFeignClient authenticationFeignClient;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Value("${spring.mail.username}")
//    private String fromEmail;


    @Override
    public TeamMemberResponse createTeamMember(TeamMemberRequest teamMemberRequest, Long companyId, Long createdById) {

//        UserRequest userData = authenticationFeignClient.getUserId(createdById);
//        if (userData == null) {
//            throw new IllegalArgumentException("User not found with ID: " + createdById);
//        }

        Optional<Company> companySavedData = companyRepository.findById(companyId);
        if (companySavedData.isPresent()) {
            Company companyData = companySavedData.get();


            try {
                if (teamMemberRequest == null) {
                    throw new NullPointerException("Please Enter Team Member Data");
                }

                String randomPassword = passwordController.generateRandomPassword();

                TeamMember teamMember = new TeamMember();
//                teamMember.setMemberId(teamMemberRequest.getMemberId());
//                teamMember.setMemberRole(teamMemberRequest.getAccessType());
//                Long companyId = teamData.getCompany().getId();
                teamMember.setCompanyId(companyId);
//                teamMember.setCompanyId(teamMemberRequest.getCompanyId());  // Set the company_id from the team
                teamMember.setMemberName(teamMemberRequest.getMemberName());
                teamMember.setMemberMail(teamMemberRequest.getMemberMail());
                teamMember.setMemberMobile(teamMemberRequest.getMemberMobile());
//                teamMember.setAccessTypeName(teamMemberRequest.get());
                teamMember.setTypeOfResource(teamMemberRequest.getTypeOfResource());
                teamMember.setCreatedAt(new Date());
                teamMember.setUpdatedAt(new Date());
                teamMember.setEnable(teamMemberRequest.isEnable());
                teamMember.setAccessTypeName(teamMemberRequest.getAccessTypeName());
//                teamMember.setTeam(teamData);
                teamMember.setCreatedById(createdById);
//                teamMember.setAccessType(teamMemberRequest.getAccessType());
//                teamMember.setAccessTypeId(teamMemberRequest.getAccessTypeId());

//                teamMember.setPassword(randomPassword);

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
//                teamMemberResponse.setAccessTypeId(teamMember.getAccessTypeId());
                teamMemberResponse.setAccessTypeName(teamMember.getAccessTypeName());
//                teamMemberResponse.setAccessType(teamMember.getAccessType());
                teamMemberResponse.setCompanyId(teamMember.getCompanyId());


                UserRequest userRequest = new UserRequest();
//
                userRequest.setFirstName(teamMemberResponse.getMemberName());
                userRequest.setEmail(teamMemberResponse.getMemberMail());
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
                userRequest.setCompany_id(1L);
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
//    private void sendInvitationEmail(TeamMemberRequest teamMemberRequest) {
//
//
//        // Construct email content
//        String subject = "Invitation to Join Law Zoom Team as ";
//        String body = "Dear Team ,\n\n"  + teamMemberRequest.getMemberName() + "\n\n"
//                + "You have been added to our team. Your username is your email address"
//                + "Please click on the following link to set up your account and change your password:\n"
//                + "hyperlink" + teamMemberRequest.getMemberMail();
//
//        // Send email
//        emailService.sendEmail(teamMemberRequest.getMemberMail(), subject, body);
//    }
//    public void sendInvitationEmail(String toEmail, String otp, String name) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//            helper.setFrom(fromEmail);
//            helper.setTo(toEmail);
//            helper.setSubject("Law Zoom - OTP Verification");
//
//            Context context = new Context();
//            context.setVariable("otp", otp);
//            context.setVariable("name", name);
//
//            String emailContent = templateEngine.process("otp-email-template", context);
//
//            helper.setText(emailContent, true);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        javaMailSender.send(message);
//    }
//write logic for fetch all team and member data should come team1 so there all team memeber for your reference i send repsonse accordingly this write code i provicde controller ,repo now write serviceipmpl

    @Override
    public TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest teamMemberRequest) {
        Optional<TeamMember> optionalTeamMember = teamMemberRepository.findById(id);

        if (!optionalTeamMember.isPresent()) {

            String errorMessage = "TeamMember with ID " + id + " not found";
            throw new EntityNotFoundException(errorMessage);
        }

        TeamMember teamMember = optionalTeamMember.get();
//        teamMember.setMemberId(teamMemberRequest.getMemberId());
//        teamMember.setMemberRole(teamMemberRequest.getRole());
        teamMember.setMemberName(teamMemberRequest.getMemberName());
        teamMember.setMemberMail(teamMemberRequest.getMemberMail());
        teamMember.setMemberMobile(teamMemberRequest.getMemberMobile());
        teamMember.setCreatedAt(new Date());
        teamMember.setUpdatedAt(new Date());
//        teamMember.setAccessType(teamMemberRequest.getAccessType());
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

// i want getAllTeamwithTeammber API using spring boot where where all team will store as key and value as list of teammembers so data will fetch as team and list of team memeber and then second then there team memebrs

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
            teamMemberResponse.setCompanyId(teamMember.getCompanyId());
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
            Company company = companyRepository.findById(teamMember.getCompanyId()).orElse(null);

            if (company != null) {
                return new TeamMemberDetailsResponse(
                        teamMember.getMemberName(),
                        company.getCompanyName(),
                        teamMember.getAccessTypeName()
                        // Initialize other fields as needed
                );
            }
        }
        return null;
    }



}
//
//    @Override
//    public List<Team> getTeamWithAllTeamMember(Long companyId) {
//        List<Team> teamList = teamRepository.findAllByCompanyId(companyId);
//
//            return teamList;
//        }


//    @Override
//    public List<Team> getAllTeam() {
//        return null;
//    }
//}
//        System.out.println(teams+"print Team");
//        List<TeamMemberResponse> teamResponses = new ArrayList<>();
//
//        for (Team team : teams) {
//            TeamMemberResponse teamWithMembersResponse = new TeamMemberResponse();
////            teamWithMembersResponse.setTeamName(team.getTeamName());
//
//            List<TeamMember> teamMembers = teamMemberRepository.findAllByTeamId(team.getId());
//            List<TeamMemberResponse> teamMemberResponses = new ArrayList<>();
//
//            for (TeamMember teamMember : teamMembers) {
//                TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
//                teamMemberResponses.add(teamMemberResponse);
//            }
//
////            teamWithMembersResponse.setTeamMembers(teamMemberResponses);
//            teamResponses.add(teamWithMembersResponse);
//        }
//
//        return teamResponses;

//
//    private String generateRandomPassword() {
//
//        String charKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        Random rand = new Random();
//        StringBuilder password = new StringBuilder();
//
//        for (int i = 0; i < 6; i++) {
//            password.append(charKey.charAt(rand.nextInt(charKey.length())));
//        }
//
//        return password.toString();
//    }
//
//    private void sendPasswordEmail(String toEmail, String password) {
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(fromEmail);
//        mailMessage.setTo(toEmail);
//        mailMessage.setSubject("Your Temporary Password");
//        mailMessage.setText("Your temporary password is: " + password);
//
//        javaMailSender.send(mailMessage);
//    }







