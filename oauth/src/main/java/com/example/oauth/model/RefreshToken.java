package com.example.oauth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60)
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String email;

    private String token;
}
