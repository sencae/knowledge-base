package com.diploma.knowledgebase.model;

import com.diploma.knowledgebase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);
    User getById(Long id);
}
