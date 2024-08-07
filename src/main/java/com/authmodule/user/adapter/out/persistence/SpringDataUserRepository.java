package com.authmodule.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmail(String email);
    Optional<UserJpaEntity> findByNickname(String email);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<UserJpaEntity> findByProviderAndProviderId(String provider, String providerId);
}
