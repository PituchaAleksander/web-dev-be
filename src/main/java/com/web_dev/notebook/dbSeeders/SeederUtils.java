package com.web_dev.notebook.dbSeeders;

import com.web_dev.notebook.securityJwt.domain.models.ERole;
import com.web_dev.notebook.securityJwt.domain.models.Role;
import com.web_dev.notebook.securityJwt.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class SeederUtils implements ISeeder{

    RoleRepository roleRepository;

    static void AssureRolesExistsInDb(RoleRepository roleRepository) {
        log.info("Assuring roles are in db.");
        for (var eRole : ERole.values()){
            if (roleRepository.findByName(eRole).isEmpty()) {
                roleRepository.insert(new Role(eRole));
            }
        }
    }

    @Override
    public void seed() {
        log.info("Seeding roles.");
        for (var eRole : ERole.values()){
            if (roleRepository.findByName(eRole).isEmpty()) {
                log.info("Inserting role:"+eRole.toString());
                roleRepository.insert(new Role(eRole));
            }
        }
    }

    @Override
    public void resetDb() {

        log.info("Removing all roles.");
        roleRepository.deleteAll();
    }


}
