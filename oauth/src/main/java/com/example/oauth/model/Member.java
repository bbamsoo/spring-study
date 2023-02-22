package com.example.oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String password;

    private String name;

    private String email;

    private String refreshToken;

    private String imageUrl;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public void authorizeMember(){
        this.role = Role.USER;
    }
}
