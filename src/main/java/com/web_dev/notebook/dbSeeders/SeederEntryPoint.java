package com.web_dev.notebook.dbSeeders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@Slf4j
public class SeederEntryPoint implements ISeeder{

    private final UserPersonSeeder userPersonSeeder;
    private final BookSeeder bookSeeder;
    private final RoleSeeder roleSeeder;

    @Override
    public void seed() {
        bookSeeder.seed();
        roleSeeder.seed();
        userPersonSeeder.seed();
    }

    @Override
    public void resetDb() {
        bookSeeder.resetDb();
        roleSeeder.resetDb();
        userPersonSeeder.resetDb();
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            resetDb();
            seed();
        };
    }
}
