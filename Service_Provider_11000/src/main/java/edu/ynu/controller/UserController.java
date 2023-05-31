package edu.ynu.controller;

import edu.ynu.entity.CommonResult;
import edu.ynu.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 开启动态刷新功能
@RequestMapping("/user")
public class UserController {

    @Value("${msg}")
    private String msg;

    @GetMapping("/hello")
    public String Hello() {
        return "Hello World";
    }

    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable Integer userId) {
        User u = new User(userId, "小明", "123456");
        return new CommonResult<>(200, "success(11000)" + msg, u);
    }
}
