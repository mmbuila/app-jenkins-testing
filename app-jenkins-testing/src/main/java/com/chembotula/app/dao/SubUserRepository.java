package com.chembotula.app.dao;

import com.chembotula.app.models.User;
import com.chembotula.app.utils.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubUserRepository implements UserRepository {

    private Map<Long, User> usersMap;

    public SubUserRepository() {
        this.usersMap = new HashMap<>();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return usersMap
                .values()
                .stream()
                .filter(user -> user.getPassword().equalsIgnoreCase(password) && user.getUsername().equalsIgnoreCase(username))
                .findAny()
                .orElseThrow(()-> new UserNotFoundException("Username or password not found : " + username));
    }

    @Override
    public User save(User user) {
        usersMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        usersMap.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(User user) {
        usersMap.remove(user.getId());
    }

    @Override
    public void deleteById(Long id) {
        usersMap.remove(id);
    }

    @Override
    public User findById(Long id) {
        return usersMap.get(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(usersMap.values());
    }
}
