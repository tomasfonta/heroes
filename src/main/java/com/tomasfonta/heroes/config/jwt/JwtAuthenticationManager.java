package com.tomasfonta.heroes.config.jwt;

import com.tomasfonta.heroes.error.UserNotFoundException;
import com.tomasfonta.heroes.model.User;
import com.tomasfonta.heroes.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public JwtAuthenticationManager(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findUserByName(name)
                .orElseThrow(() -> new UserNotFoundException(String.format("Username %s not Found", name)));

        if (encoder.matches(password, user.getPassword())) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getId(),
                    user.getPassword(),
                    user.getRole().getGrantedAuthorities());
            return usernamePasswordAuthenticationToken;
        } else {
            throw new UserNotFoundException("Invalid password");
        }
    }

}
