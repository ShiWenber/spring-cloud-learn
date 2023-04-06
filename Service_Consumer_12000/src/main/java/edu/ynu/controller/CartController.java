package edu.ynu.controller;

import edu.ynu.entity.CommonResult;
import edu.ynu.entity.User;
import edu.ynu.fegin.UserFeignService;
import edu.ynu.loadbalance.CustomLoadBalanceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
@LoadBalancerClient(name = "provider-server", configuration = CustomLoadBalanceConfiguration.class)
public class CartController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/hello")
    public String hello() {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("PROVIDER-SERVER");
//        instanceList.forEach(item -> System.out.println(item.getHost() + "\t" + item.getPort()));
        System.out.println(instanceList.size());
        System.out.println(instanceList.get(0).getHost());
        System.out.println(instanceList.get(0).getPort());
        ServiceInstance instance = instanceList.get(0); // 从服务列表中获取一个服务实例
        assert instance != null;
//        // 使用硬编码url远程调用一个restapi
//        return restTemplate.getForObject(
//                "http://localhost:8099/user/hello",
//                String.class
//        );
//        return restTemplate.getForObject(
//                "http://" + instance.getHost() + ":" + instance.getPort() + "/user/hello",
//                String.class
//        );
        return userFeignService.hello();
    }

    @GetMapping("addCart/{userId}")
    public CommonResult<User> addCart(@PathVariable Integer userId) {
//    这种字符串拼接可能导致注入攻击
//        return restTemplate.getForObject(
//                "http://localhost:8099/user/getUserById/" + userId,
//                CommonResult.class
//        );
        List<ServiceInstance> instanceList = discoveryClient.getInstances("PROVIDER-SERVER");

        ServiceInstance serviceInstance = instanceList.get(0);

//        CommonResult<User> result = restTemplate.getForObject(
//                "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/getUserById/" + userId.toString(),
//                CommonResult.class
//        );
//
//        return result;
        return userFeignService.getUserById(userId);
    }

    @PostMapping("addCart2/{userId}")
    public CommonResult<User> addCart2(@PathVariable Integer userId) {
        return userFeignService.getUserById(userId);
    }
}
