package com.tyb1222.service.impl;

import com.tyb1222.beans.User;
import com.tyb1222.dao.UserDao;
import com.tyb1222.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() throws Exception {
        return userDao.getUsers();
    }

    @Override
    public User findUserById(User user) throws Exception {
        return userDao.findUserById(user);
    }

    @Override
    public List<User> findUsers(String param) throws Exception {
        return null;
    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public void deleteUser(User user) throws Exception {

    }

    @Override
    public User login(User user) throws Exception {
        return null;
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }
}
