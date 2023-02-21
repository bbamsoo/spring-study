package com.example.oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String refreshToken;

    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SocialType socialId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Getter
    @RequiredArgsConstructor
    public enum Role{
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");
        private final String key;
    }

    public enum SocialType{
        KAKAO, NAVER, GOOGLE;
    }

    public void authorizeMember(){
        this.role = Role.USER;
    }
}
