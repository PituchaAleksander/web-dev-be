package com.web_dev.notebook.securityJwt.domain.repository;

import java.util.Optional;

import com.web_dev.notebook.securityJwt.domain.models.ERole;
import com.web_dev.notebook.securityJwt.domain.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);


}
