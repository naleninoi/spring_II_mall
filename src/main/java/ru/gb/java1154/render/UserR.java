package ru.gb.java1154.render;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.gb.java1154.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class UserR {

    Long id;
    String email;
    String firstName;
    String lastName;
    List<String> roles;
    boolean isActive;

    public UserR(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.isActive = user.isActive();
        this.roles = user.getRoles().stream()
                .map(role -> role.getRoleType().toString())
                .collect(Collectors.toList());
    }
}
