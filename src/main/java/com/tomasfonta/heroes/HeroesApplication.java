package com.tomasfonta.heroes;

import com.tomasfonta.heroes.config.security.ApplicationRoles;
import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.User;
import com.tomasfonta.heroes.repository.HeroRepository;
import com.tomasfonta.heroes.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class HeroesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroesApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(HeroRepository heroRepository,
                             UserRepository userRepository) {

        return args -> {

            Hero hero = Hero.builder()
                    .name("Batman")
                    .slug("Batman")
                    .build();

            Hero hero2 = Hero.builder()
                    .name("Robin")
                    .slug("Robin")
                    .build();

            heroRepository.saveAll(List.of(hero, hero2));

            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            User user = User.builder()
                    .password(encoder.encode("password"))
                    .name("user")
                    .role(ApplicationRoles.USER)
                    .build();

            User admin = User.builder()
                    .password(encoder.encode("password"))
                    .name("admin")
                    .role(ApplicationRoles.ADMIN)
                    .build();

            userRepository.save(admin);
            userRepository.save(user);

        };
    }
}



