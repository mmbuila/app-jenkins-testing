package com.chembotula.app;

import com.chembotula.app.dao.RoleRepository;
import com.chembotula.app.dao.SubRoleRepository;
import com.chembotula.app.models.Role;
import com.chembotula.app.utils.RoleNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleRepositoryTest {

    private RoleRepository roleRepository;
    private Role role;

    @Before
    public void init() {
        roleRepository = new SubRoleRepository();
        role = new Role(1L, "READ-ONLY");
    }

    @Test
    public void save() {
        Role pRole = roleRepository.save(role);
        Assert.assertNotNull(pRole);
        Assert.assertEquals(pRole.getId(), new Long(1L));
    }

    @Test
    public void update() {
        Role pRole = roleRepository.save(role);
        Assert.assertNotNull(pRole);
        pRole.setRoleName("MANAGER");
        Role pRoleUpdate = roleRepository.update(role);
        Assert.assertEquals(pRoleUpdate.getRoleName(), pRole.getRoleName());
    }

    @Test
    public void delete() {
        roleRepository.save(role);
        roleRepository.delete(role);
        Assert.assertTrue(roleRepository.findAll().isEmpty());
    }

    @Test
    public void deleteById() {
        roleRepository.save(role);
        roleRepository.deleteById(role.getId());
        Assert.assertTrue(roleRepository.findAll().isEmpty());
    }

    @Test
    public void findById() {
        roleRepository.save(role);
        Role pRole = roleRepository.findById(role.getId());
        Assert.assertNotNull(pRole);
    }

    @Test
    public void findAll() {
        roleRepository.save(role);
        Assert.assertFalse(roleRepository.findAll().isEmpty());
    }

    @Test
    public void findByRoleName() {
        roleRepository.save(role);
        Role pRole = roleRepository.findByRoleName("READ-ONLY");
        Assert.assertNotNull(pRole);
    }

    @Test(expected = RoleNotFoundException.class)
    public void findRoleNotFoundException() {
        roleRepository.save(role);
        Role pRole = roleRepository.findByRoleName("ADMIN");
        Assert.assertNotNull(pRole);
    }
}
