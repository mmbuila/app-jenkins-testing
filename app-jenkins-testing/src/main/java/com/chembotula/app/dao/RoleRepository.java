package com.chembotula.app.dao;

import com.chembotula.app.models.Role;

public interface RoleRepository extends Repository<Role, Long> {
    Role findByRoleName(String roleName);
    void initialize();
}
