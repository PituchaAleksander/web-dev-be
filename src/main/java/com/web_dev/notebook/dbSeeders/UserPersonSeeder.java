package com.web_dev.notebook.dbSeeders;

import com.web_dev.notebook.exceptions.NotFoundException;
import com.web_dev.notebook.securityJwt.application.controller.AuthController;
import com.web_dev.notebook.securityJwt.domain.models.ERole;
import com.web_dev.notebook.securityJwt.domain.models.Role;
import com.web_dev.notebook.securityJwt.domain.models.User;
import com.web_dev.notebook.securityJwt.application.request.SignupRequest;
import com.web_dev.notebook.securityJwt.domain.repository.RoleRepository;
import com.web_dev.notebook.securityJwt.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@AllArgsConstructor
@Component
@Slf4j
public class UserPersonSeeder implements ISeeder {

    AuthController authController;
    RoleRepository roleRepository;
    UserRepository userRepository;

    public void seed(){
        log.info("Seeding UserPerson objects.");
        SeederUtils.AssureRolesExistsInDb(roleRepository);


        assertAdminAndModAndUserCreation(authController, userRepository);
    }

    @Override
    public void resetDb() {
        log.info("Reseting all db.");
        roleRepository.deleteAll();
        userRepository.deleteAll();
        log.info("Reset db end.");
    }

    private void assertAdminAndModAndUserCreation(AuthController authController, UserRepository userRepository) {
        Role userRole = roleRepository.findByName(ERole.ROLE_BASIC_USER).get();
        Role admin = roleRepository.findByName(ERole.ROLE_ADMIN).get();

        if (userRepository.findByUsername("admin").isEmpty()) {
            authController.registerUser(new SignupRequest("admin", "admin@gmail.com", "password"));
            User adminUser = userRepository.findByEmail("admin@gmail.com").orElseThrow(() -> new NotFoundException("User", "admin@gmail.com"));
            adminUser.setRoles(Set.of(admin, userRole));
            userRepository.save(adminUser);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            authController.registerUser(new SignupRequest("user", "user@gmail.com", "password"));
            User user = userRepository.findByEmail("user@gmail.com").orElseThrow(() -> new NotFoundException("User", "user@gmail.com"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }

    }

}

