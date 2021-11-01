package ru.gb.java1154.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.java1154.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
