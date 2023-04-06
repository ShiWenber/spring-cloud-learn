package edu.ynu.fegin;

import edu.ynu.entity.CommonResult;
import edu.ynu.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("provider-server")
public interface UserFeignService {

    @GetMapping("/user/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId);

    @GetMapping("/user/hello")
    public String hello();

}
