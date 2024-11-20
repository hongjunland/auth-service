package com.authmodule.common.jwt.refresh.repository;

import com.authmodule.common.jwt.refresh.entity.RefreshTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity, Long> {

}
