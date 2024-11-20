package com.authmodule.common.jwt.refresh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenJpaEntity {
    @Id
    private Long id; // 사용자 이메일을 기본 키로 사용
    private String token; // 리프레시 토큰
    private Instant expiration; // 토큰 만료 시간
}
