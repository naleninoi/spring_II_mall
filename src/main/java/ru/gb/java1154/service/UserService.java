package ru.gb.java1154.service;


import ru.gb.java1154.dto.UserDto;
import ru.gb.java1154.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    boolean checkUsernameOccupied(String username);

    void register(UserDto userDto);

}
