package edu.ynu.fegin;

import edu.ynu.entity.CommonResult;
import edu.ynu.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("provider-server")
public interface UserFeignService {

    @GetMapping("/user/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId);

    @GetMapping("/user/hello")
    public String hello();

    @PostMapping("/user/addUser")
    public CommonResult<User> addUser(User user);

    @PutMapping("/user/updateUser")
    public CommonResult<User> updateUser(User user);

    @GetMapping("/user/deleteUserById/{userId}")
    public CommonResult<User> deleteUserById(@PathVariable("userId") Integer userId);

}
