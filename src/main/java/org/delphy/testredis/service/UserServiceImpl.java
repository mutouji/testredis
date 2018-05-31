package org.delphy.testredis.service;

import java.util.Random;

public class UserServiceImpl implements IUserService {

    public void login(String userName, String password) {
        try {
            Thread.sleep(new Random(47).nextInt(100));
        } catch (InterruptedException e) {}
        System.out.println("UserService: 用户" + userName + "登录成功");
    }
}
