package ru.gb.java1154.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.java1154.entity.Role;
import ru.gb.java1154.enums.RoleType;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
