package edu.ynu.ecs.feign;

import com.github.pagehelper.Page;
import edu.ynu.ecs.entities.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-server")
public interface UserFeignService {


    @GetMapping("/user/{id}")
    Customer queryById(@PathVariable("id") String id);

    @GetMapping("/hello")
    String hello();

    @GetMapping("/changePassword")
    Customer changePassword(@RequestParam String username, @RequestParam String password, @RequestParam String newPassword);

    @GetMapping("/menu")
    Page<Item> menu(@RequestParam int pageIndex, @RequestParam int pageSize);

    @GetMapping("/businessList")
    Page<Business> businessList(@RequestParam int pageIndex, @RequestParam int pageSize);

    @GetMapping("/addLineItem")
    LineItem addLineItem(@RequestParam String id, @RequestParam String orderId, @RequestParam String itemId, @RequestParam int quantity);

    @PostMapping("/buy/{customerId}/{shopId}/{itemId}/{quantity}")
    LineItem selectItem(@PathVariable String customerId, @PathVariable String shopId, @PathVariable String itemId, @PathVariable int quantity);

    @GetMapping("/lineItemList/{orderId}")
    List<LineItem> lineItemList(@PathVariable String orderId);

    @GetMapping("/deleteLineItem")
    LineItem deleteLineItem(@RequestParam String id, @RequestParam String orderId, @RequestParam String itemId);

    @GetMapping("/orderList/{customerId}/{orderId}")
    List<Order> orderList(@PathVariable String customerId, @PathVariable String orderId);

    @GetMapping("/addressList")
    List<DeliveryAddress> addressList(@RequestParam String id);

    @PostMapping("/addAddress/{id}")
    DeliveryAddress addAddress(@RequestBody DeliveryAddress deliveryAddress, @PathVariable String id);

    @GetMapping("/deleteAddress")
    DeliveryAddress deleteAddress(@RequestParam String id, @RequestParam String addressId);

    @GetMapping("/changeAddress")
    DeliveryAddress changeAddress(@RequestParam String id, @RequestParam String addressId, @RequestParam String address);


}
