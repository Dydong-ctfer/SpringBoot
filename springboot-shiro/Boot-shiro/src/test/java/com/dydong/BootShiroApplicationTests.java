package com.dydong;

import com.dydong.mapper.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootShiroApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        String name="user1";
        System.out.println(userService.queryUserByName(name));
    }

}
