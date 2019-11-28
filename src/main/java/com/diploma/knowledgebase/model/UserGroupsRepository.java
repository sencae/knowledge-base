package com.diploma.knowledgebase.model;

import com.diploma.knowledgebase.entities.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupsRepository extends JpaRepository<UserGroups, Long> {
    UserGroups getById(Long id);
    UserGroups getUserGroupsByGroupName(String name);
}
