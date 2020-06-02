package com.chembotula.app.dao;

import com.chembotula.app.models.User;

public interface UserRepository extends Repository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
