package com.tyb1222.service;

import com.tyb1222.beans.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有的用户数据
     * @return
     * @throws Exception
     */
    List<User> getUsers() throws Exception;

    /**
     * 根据用户名称查询单个用户
     */
    User findUserById(User user) throws Exception;

    /**
     * 模糊查询用户数据
     */
    List<User> findUsers(String param) throws Exception;

    /**
     * 根据用户id更新某个用户的信息
     */
    void updateUser(User user) throws Exception;

    /**
     * 删除某条用户记录
     */
    void deleteUser(User user) throws Exception;

    /**
     * 登录操作
     */
    User login(User user) throws Exception;

    User getUser(int id);

}
