package com.example.oauth.repository;

import com.example.oauth.model.Member;
import com.example.oauth.model.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
