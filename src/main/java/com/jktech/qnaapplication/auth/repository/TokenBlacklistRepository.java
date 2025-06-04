package com.jktech.qnaapplication.auth.repository;

import com.jktech.qnaapplication.auth.model.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, String> {
    boolean existsByToken(String token);
}
