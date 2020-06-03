package com.chembotula.app.dao;

import com.chembotula.app.models.Role;
import com.chembotula.app.utils.RoleNotFoundException;;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SubRoleRepository implements RoleRepository {

    private Map<Long, Role> rolesMap;
    private AtomicLong atomicLong;

    public SubRoleRepository() {
        rolesMap = new HashMap<>();
        atomicLong = new AtomicLong();
    }

    @Override
    public void initialize() {
        Arrays.asList("ADMIN", "USER", "MANAGER")
                .forEach(roleName -> {
                    long id = atomicLong.getAndIncrement();
                    rolesMap.put(id, new Role(id, roleName));
                });
    }

    @Override
    public Role findByRoleName(String roleName) {
        return rolesMap
                .values()
                .stream()
                .filter(role -> role.getRoleName().equalsIgnoreCase(roleName))
                .findAny()
                .orElseThrow(()-> new RoleNotFoundException("RoleName not found : " + roleName));
    }

    @Override
    public Role save(Role role) {
        rolesMap.put(role.getId(), role);
        return role;
    }

    @Override
    public Role update(Role role) {
        rolesMap.put(role.getId(), role);
        return role;
    }

    @Override
    public void delete(Role role) {
        if(role != null) {
            rolesMap.remove(role.getId());
        } else {
            throw new RoleNotFoundException("Rule not exists");
        }
    }

    @Override
    public void deleteById(Long id) {
        rolesMap.remove(id);
    }

    @Override
    public Role findById(Long id) {
        return rolesMap.get(id);
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(rolesMap.values());
    }
}
