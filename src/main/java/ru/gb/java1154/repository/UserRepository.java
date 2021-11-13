package ru.gb.java1154.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.java1154.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByIsActiveIsTrue();

}
