package com.tyb1222.dao;

import com.tyb1222.beans.User;

import java.util.List;

public interface UserDao {

    User findUserById(User user);

    List<User> getUsers();

    User getUser(int id);
}
