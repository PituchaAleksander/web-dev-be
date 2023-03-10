package com.web_dev.notebook.dbSeeders;

import com.web_dev.notebook.securityJwt.domain.models.ERole;
import com.web_dev.notebook.securityJwt.domain.models.Role;
import com.web_dev.notebook.securityJwt.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@AllArgsConstructor
public class RoleSeeder implements ISeeder {


    RoleRepository roleRepository;

    @Override
    public void seed() {
        Arrays.stream(ERole.values()).forEach(eRole ->
                roleRepository.findByName(eRole)
                        .ifPresentOrElse(role -> {log.info("Role: " + eRole.toString() + " already exist.");
                                },
                                () -> {
                                    roleRepository.save(new Role(eRole));
                                    log.info("Inserted role: " + eRole.toString());
                                }));

    }

    @Override
    public void resetDb() {
        roleRepository.deleteAll();
    }

}
