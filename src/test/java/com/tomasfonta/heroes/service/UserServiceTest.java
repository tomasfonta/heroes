package com.tomasfonta.heroes.service;

import com.tomasfonta.heroes.config.security.ApplicationRoles;
import com.tomasfonta.heroes.model.User;
import com.tomasfonta.heroes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(generateUser()));
        List<User> users = userService.getAllUsers();
        verify(userRepository).findAll();
        assertThat(users).isNotNull();
        assertThat(users).isNotEmpty();
        assertThat(users).contains(generateUser());
    }

    private User generateUser() {
        return User.builder()
                .name("Tomas")
                .password("password")
                .role(ApplicationRoles.USER)
                .id(1L)
                .build();
    }
}