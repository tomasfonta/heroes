package com.tomasfonta.heroes.repository;

import com.tomasfonta.heroes.config.security.ApplicationRoles;
import com.tomasfonta.heroes.model.Hero;
import com.tomasfonta.heroes.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    void findByName() {
        User user = generateUser();
        userRepository.save(user);
        Optional<User> expected = userRepository.findUserByName(user.getName());

        assertThat(expected).isNotEmpty();
        assertThat(expected).isPresent();
        assertThat(expected.get().getName()).isEqualTo(user.getName());
        assertThat(expected.get().getPassword()).isEqualTo(user.getPassword());
        assertThat(expected.get().getRole()).isEqualTo(user.getRole());
    }

    @Test
    void findByNonExisting() {
        Optional<User> expected = userRepository.findUserByName("NonExistingName");
        assertThat(expected).isEmpty();
        assertThat(expected).isNotPresent();
    }

    private User generateUser() {
        return User.builder()
                .name("Tomas")
                .password("Password")
                .role(ApplicationRoles.USER)
                .build();
    }
}