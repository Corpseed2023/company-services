package com.lawzoom.companyservice.repository.team;

import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember,Long> {
    List<TeamMember> findAllByTeamId(Long teamId);

    TeamMember findByMemberMail(String memberMail);
}
