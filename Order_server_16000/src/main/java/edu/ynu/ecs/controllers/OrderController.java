package edu.ynu.ecs.controllers;

import com.netflix.discovery.converters.Auto;
import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.CommonResult;
import edu.ynu.ecs.entities.Customer;
import edu.ynu.ecs.entities.Order;
import edu.ynu.ecs.entities.Shop;
import edu.ynu.ecs.feign.ShopFeignService;
import edu.ynu.ecs.feign.UserFeignService;
import edu.ynu.ecs.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@Tag(name = "订单控制器 V1", description = "订单相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController extends AbstractTypedController<Order, String> {
    @Resource
    OrderService orderService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private ShopFeignService shopFeignService;


    OrderController(OrderService service) {
        this.svcContext = service;
        this.orderService = service;
    }

    //查询订单
    @GetMapping(value = "/queryOrder/{userid}/{shopid}")
    public Order queryOrder(@PathVariable String userid, @PathVariable String shopid) {
        Customer customer = userFeignService.queryById(userid);
        Shop shop = shopFeignService.queryById(shopid);
        return orderService.findByCustomerAndShop(customer, shop);
    }

    //新建订单
    //    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
//    @Bulkhead(name = "bulkheadA", fallbackMethod = "fallback", type = Bulkhead.Type.SEMAPHORE) // SEMAPHORE 表示用信号量方式来隔离
    @Bulkhead(name = "bulkheadB", fallbackMethod = "fallback", type = Bulkhead.Type.THREADPOOL)
    // THREADPOOL 表示用线程池方式来隔离
    @RateLimiter(name = "ratelimiterA", fallbackMethod = "fallback")
    @PostMapping(value = "/createOrder")
    public void createOrder(@RequestBody Order order) {
        log.info("TRUE");
        orderService.createOrder(order);
    }


    public CommonResult<Order> fallback(Integer userId, Throwable e) {
        e.printStackTrace();
        System.out.println("fallback调用");
        CommonResult<Order> result = new CommonResult<>(400, "当前用户服务不正常,服务降级稍后再试", new User());
        return result;
    }

}
