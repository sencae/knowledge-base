package com.diploma.knowledgebase.services.registrationService;

import com.diploma.knowledgebase.entities.ConfirmationToken;
import com.diploma.knowledgebase.model.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ConfirmationToken getByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.getByConfirmationToken(confirmationToken);
    }

    public void delete(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }

    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }
}
