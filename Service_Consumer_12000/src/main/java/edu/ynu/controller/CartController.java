package edu.ynu.controller;

import edu.ynu.entity.CommonResult;
import edu.ynu.entity.User;
import edu.ynu.fegin.UserFeignService;
import edu.ynu.loadbalance.CustomLoadBalanceConfiguration;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cart")
//@LoadBalancerClient(name = "provider-server", configuration = CustomLoadBalanceConfiguration.class) // 随机
@LoadBalancerClient(name = "provider-server", configuration = RoundRobinLoadBalancer.class) // 采用默认的负载均衡策略
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

    @GetMapping("/addCart/{userId}")
//    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
//    @Bulkhead(name = "bulkheadA", fallbackMethod = "fallback", type = Bulkhead.Type.SEMAPHORE) // SEMAPHORE 表示用信号量方式来隔离
    public CommonResult<User> addCart(@PathVariable Integer userId) {
        System.out.println("进入方法");
        try {
            Thread.sleep(1000); // 测试慢调用
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonResult<User> list = userFeignService.getUserById(userId);
        System.out.println("出方法");
        return list;
    }


    @GetMapping("/addCart1/{userId}")
    @Bulkhead(name = "bulkheadB", fallbackMethod = "fallback", type = Bulkhead.Type.THREADPOOL) // THREADPOOL 表示用线程池方式来隔离
    public CompletableFuture<User> addCart1(@PathVariable Integer userId) {
        System.out.println("进入方法");
        CompletableFuture<User> result = CompletableFuture.supplyAsync(() -> {
            return userFeignService.getUserById(userId).getResult();
        });
        System.out.println("出方法");
        return result;
    }

    public CommonResult<User> fallback(Integer userId, Throwable e) {
        e.printStackTrace();
        System.out.println("fallback调用");
        CommonResult<User> result = new CommonResult<>(400, "当前用户服务不正常,服务降级稍后再试", new User());
        return result;
    }

    @PostMapping("/addUser")
    public CommonResult<User> addUser(@RequestBody User user) {
        CommonResult<User> result = userFeignService.addUser(user);
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject(
//                "http://localhost:11000/user/addUser",
//                user,
//                CommonResult.class
//        );
        return result;
    }

    @PutMapping("/updateUser")
    public CommonResult<User> updateUser(@RequestBody User user) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put(
//                "http://localhost:11000/user/updateUser",
//                user,
//                CommonResult.class
//        );
//        return new CommonResult<>(200, "success(11000)" + "put", user);
        CommonResult<User> result = userFeignService.updateUser(user);
        return result;
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public CommonResult<User> deleteUserById(@PathVariable Integer userId) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(
//                "http://localhost:11000/user/deleteUserById/" + userId,
//                null,
//                CommonResult.class
//        );
//        return new CommonResult<>(200, "success(11000)" + "delete", new User());
        CommonResult<User> result = userFeignService.deleteUserById(userId);
        return result;
    }


    @PostMapping("addCart2/{userId}")
    public CommonResult<User> addCart2(@PathVariable Integer userId) {
        return userFeignService.getUserById(userId);
    }
}
