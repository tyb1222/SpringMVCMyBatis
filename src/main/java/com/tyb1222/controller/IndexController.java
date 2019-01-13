package com.tyb1222.controller;

import com.tyb1222.beans.User;
import com.tyb1222.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    /**
     * 获取用户数据
     *
     * @throws Exception
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        logger.info("begin to get user");
        List<User> list = new ArrayList<>();
        try {
            list = userService.getUsers();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser() {
        logger.info("begin to get user");
        User result = null;
        try {
            result = userService.getUser(1);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 模糊查询 用户名或者邮箱
     */
    @RequestMapping(value = "/queryusers", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public List<User> queryUserData(@RequestParam(value = "data") String param) {
        logger.debug("先打印下参数：" + param);
        List<User> list = new ArrayList<>();
        try {
            list = userService.findUsers(param);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return list;
    }

    /**
     * Map方式
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/usersList", consumes = "application/json")
    @ResponseBody
    public Map queryMapUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            String nickName = request.getParameter("data");
            logger.debug("http-->" + nickName);
            if (!nickName.isEmpty() && !nickName.equals("null")) {
                map.put("list", userService.findUsers(nickName));
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return map;
    }

    /**
     * 查询单个用户
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public User getUser(@RequestParam(value = "userid") String userId) {
        User user = new User();
        user.setId("123");
        try {
            user = userService.findUserById(user);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return user;
    }

    /**
     * 使用body体方式的json请求
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    @ResponseBody
    public Map getUserToBody(@RequestBody User user) {
        Map<String, String> map = new HashMap<>();
        User shineUser = new User();
        shineUser.setNickname(user.getNickname());
        shineUser.setPassword(user.getPassword());
        try {
            shineUser = userService.login(shineUser);
            if (shineUser != null) {
                if (user.getNickname().equals(shineUser.getNickname())
                        && user.getPassword().equals(shineUser.getPassword())) {
                    map.put("username", shineUser.getNickname());
                    map.put("password", shineUser.getPassword());
                } else if (!user.getNickname().equals(shineUser.getNickname())) {

                } else if (!user.getPassword().equals(shineUser.getPassword())) {

                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/userlogin", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Map userLogin(@RequestParam(value = "username", required = true) String userName,
                         @RequestParam(value = "password", required = true) String password) {
        Map<String, String> map = new HashMap<>();
        if (!userName.isEmpty() && !password.isEmpty()) {
            User user = new User();
            user.setNickname(userName);
            user.setPassword(password);
            try {
                user = userService.login(user);
                if (user != null) {
                    if (userName.equals(user.getNickname()) && password.equals(user.getPassword())) {

                        map.put("username", user.getNickname());
                        map.put("password", user.getPassword());
                    } else if (!userName.equals(user.getNickname())) {

                    } else if (!password.equals(user.getPassword())) {

                    }
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
