package com.chembotula.app;

import com.chembotula.app.dao.SubUserRepository;
import com.chembotula.app.dao.UserRepository;
import com.chembotula.app.models.User;
import com.chembotula.app.utils.UserNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {

    private UserRepository userRepository;
    private User user;

    @Before
    public void init() {
        userRepository = new SubUserRepository();
        user = new User(1L, "michel.mbuila@gmail.com", "chembotula", true);
    }

    @Test
    public void save() {
        User pUser = userRepository.save(user);
        Assert.assertNotNull(pUser);
        Assert.assertEquals(pUser.getId(), new Long(1L));
    }

    @Test
    public void update() {
        User pUser = userRepository.save(user);
        Assert.assertNotNull(pUser);
        pUser.setPassword("michel");
        pUser.setUsername("colette.mbuila@gmail.com");
        User pUserUpdate = userRepository.update(user);
        Assert.assertTrue(pUserUpdate.getUsername().equals(pUser.getUsername()) && pUserUpdate.getPassword().equals(pUser.getPassword()));
    }

    @Test
    public void delete() {
        userRepository.save(user);
        userRepository.delete(user);
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void deleteById() {
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        Assert.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void findById() {
        userRepository.save(user);
        User pUser = userRepository.findById(user.getId());
        Assert.assertNotNull(pUser);
    }

    @Test
    public void findAll() {
        userRepository.save(user);
        Assert.assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    public void findByUsernameAndPassword() {
        userRepository.save(user);
        User pUser = userRepository.findByUsernameAndPassword("michel.mbuila@gmail.com", "chembotula");
        Assert.assertNotNull(pUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void findUserNotFoundException() {
        userRepository.save(user);
        User pUser = userRepository.findByUsernameAndPassword("michel.mbuila@gmail.com", "chembotula0069");
        Assert.assertNotNull(pUser);
    }
}
