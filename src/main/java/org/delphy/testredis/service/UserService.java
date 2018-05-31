package org.delphy.testredis.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    public void login(String userName, String password) {
        try {
            Thread.sleep(new Random(47).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("UserService: 用户" + userName + "登录成功");
    }
}
