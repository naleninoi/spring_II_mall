package ru.gb.java1154.service.implementation;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gb.java1154.entity.User;
import ru.gb.java1154.repository.UserRepository;
import ru.gb.java1154.service.SecurityService;

import java.util.Optional;


@Service
public class SecurityServiceImplementation implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return userRepository.findByEmail(currentUsername);
        }
        return Optional.empty();
    }
}
