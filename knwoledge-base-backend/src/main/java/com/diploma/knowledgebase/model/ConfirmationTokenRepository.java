package com.diploma.knowledgebase.model;

import com.diploma.knowledgebase.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken getByConfirmationToken(String confirmationToken);
}
