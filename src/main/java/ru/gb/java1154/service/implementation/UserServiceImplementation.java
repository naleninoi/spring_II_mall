package ru.gb.java1154.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.java1154.dto.UserDto;
import ru.gb.java1154.entity.Role;
import ru.gb.java1154.entity.User;
import ru.gb.java1154.enums.RoleType;
import ru.gb.java1154.repository.RoleRepository;
import ru.gb.java1154.repository.UserRepository;
import ru.gb.java1154.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Bad credentials");
        }
        User user = optionalUser.get();
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().toString()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByIsActiveIsTrue();
    }

    @Override
    public boolean checkUsernameOccupied(String username) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        return optionalUser.isPresent();
    }

    @Override
    public void register(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode( userDto.getPassword() ));
        user.setActive(true);
        Set<Role> userRoles = new HashSet<>();
        Optional<Role> roleUser = roleRepository.findByRoleType(RoleType.ROLE_CUSTOMER);
        roleUser.ifPresent(userRoles::add);
        user.setRoles(userRoles);
        userRepository.saveAndFlush(user);
    }
}
