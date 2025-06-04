package com.jktech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TokenBlacklist")
public class TokenBlacklist {
    @Id
    private String token;
    private LocalDateTime expiryDate;

    public TokenBlacklist() {}
    public TokenBlacklist(String token, LocalDateTime expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
    }
}
