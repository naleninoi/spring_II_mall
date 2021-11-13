package ru.gb.java1154.service;

import ru.gb.java1154.entity.User;

import java.util.Optional;

public interface SecurityService {

    Optional<User> getCurrentUser();

}
