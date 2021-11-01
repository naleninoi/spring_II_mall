package ru.gb.java1154.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gb.java1154.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
