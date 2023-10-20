package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.config.EmailService;
import com.lawzoom.companyservice.controller.PasswordController;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.dto.userDto.UserRequest;
import com.lawzoom.companyservice.feignClient.AuthenticationFeignClient;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.repository.TeamMemberRepository;
import com.lawzoom.companyservice.repository.TeamRepository;
import com.lawzoom.companyservice.service.TeamMemberService;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PasswordController passwordController;

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
    public TeamMemberResponse createTeamMember(TeamMemberRequest teamMemberRequest, Long teamId) {

        Optional<Team> teamSavedData = teamRepository.findById(teamId);
        if (teamSavedData.isPresent()) {
            Team teamData = teamSavedData.get();

            try {
                if (teamMemberRequest == null) {
                    throw new NullPointerException("Please Enter Team Member Data");
                }

                String randomPassword = passwordController.generateRandomPassword();

                TeamMember teamMember = new TeamMember();
//                teamMember.setMemberId(teamMemberRequest.getMemberId());
                teamMember.setMemberRole(teamMemberRequest.getRole());
                teamMember.setMemberName(teamMemberRequest.getMemberName());
                teamMember.setMemberMail(teamMemberRequest.getMemberMail());
                teamMember.setMemberMobile(teamMemberRequest.getMemberMobile());
                teamMember.setAccessType(teamMemberRequest.getAccessType());
                teamMember.setTypeOfResource(teamMemberRequest.getTypeOfResource());
                teamMember.setCreatedAt(new Date());
                teamMember.setUpdatedAt(new Date());
                teamMember.setEnable(teamMemberRequest.isEnable());
                teamMember.setTeam(teamData);
//                teamMember.setPassword(randomPassword);

                System.out.println("Got Hit");


                teamMember = teamMemberRepository.save(teamMember);

                TeamMemberResponse teamMemberResponse = new TeamMemberResponse();
                teamMemberResponse.setMemberMail(teamMember.getMemberMail());
                teamMemberResponse.setMemberName(teamMember.getMemberName());
                teamMemberResponse.setCreatedAt(teamMember.getCreatedAt());
                teamMemberResponse.setUpdatedAt(teamMember.getUpdatedAt());
                teamMemberResponse.setEnable(teamMember.isEnable());
                teamMemberResponse.setAccessType(teamMember.getAccessType());
                teamMemberResponse.setMemberMobile(teamMember.getMemberMobile());
                teamMemberResponse.setTypeOfResource(teamMember.getTypeOfResource());

                UserRequest userRequest = new UserRequest();
                userRequest.setFirstName(teamMemberResponse.getMemberName());
                userRequest.setEmail(teamMemberResponse.getMemberMail());
                userRequest.setPassword(randomPassword);
//                userRequest.setDesignation(teamMemberResponse.get);
                userRequest.setResourceType(teamMemberResponse.getTypeOfResource());
//                userRequest.setRoles(teamMemberResponse.getAccessType());

                authenticationFeignClient.createTeamMemberUsers(userRequest);
//                sendInvitationEmail(teamMemberRequest);


                System.out.println("Got Hit");

                return teamMemberResponse;

            } catch (Exception e) {
                e.printStackTrace();

                throw new RuntimeException("Failed to create team members");
            }
        }
        else {
            throw new IllegalArgumentException("Team not found with ID: " + teamId);
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
        teamMember.setAccessType(teamMemberRequest.getAccessType());
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
        teamMemberResponse.setAccessType(teamMember.getAccessType());
        teamMemberResponse.setMemberName(teamMember.getMemberName());



        System.out.println("kaushal");

        return teamMemberResponse;
    }


    @Override
    public List<TeamMemberResponse> getAllTeamMembers(Long teamId) {
        List<TeamMember> teamMembers = teamMemberRepository.findAllByTeamId(teamId);
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
            teamMemberResponse.setAccessType(teamMember.getAccessType());

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

        Optional<TeamMember> memberData= teamMemberRepository.findById(memberId);

        if (memberData.isPresent())
        {
            TeamMember membertoDelete = memberData.get();

            teamMemberRepository.delete(membertoDelete);

        }

        else {
            throw new EntityNotFoundException("Member Not present in database");
        }

    }
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


}
