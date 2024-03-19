package com.td.todolist240317.domain.member.member.repository;
import com.td.todolist240317.domain.member.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);
}
