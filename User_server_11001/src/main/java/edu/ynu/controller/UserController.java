package edu.ynu.controller;

import edu.ynu.entity.CommonResult;
import edu.ynu.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Value("${msg}")
    private int msg;

    @GetMapping("/hello")
    public String Hello() {
        return "Hello World";
    }

    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable Integer userId) {
        User u = new User(userId, "小明", "123456");
        return new CommonResult<>(200, "success(11001)"+msg, u);
    }

    @PostMapping("/addUser")
    public CommonResult<User> addUser(@RequestBody User user) {
        return new CommonResult<>(200, "success(11000)" + msg + "post", user);
    }

    @PutMapping("/updateUser")
    public CommonResult<User> updateUser(@RequestBody User user) {
        return new CommonResult<>(200, "success(11000)" + msg + "put", user);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public CommonResult<User> deleteUserById(@PathVariable Integer userId) {
        User u = new User(userId, "小明", "123456");
        return new CommonResult<>(200, "success(11000)" + msg + "delete", u);
    }

}
