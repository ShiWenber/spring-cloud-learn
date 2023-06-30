package edu.ynu.ecs.feign;

import edu.ynu.ecs.entities.Customer;
import edu.ynu.ecs.entities.Shop;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shop-server")
public interface ShopFeignService {

    @GetMapping("/item/{id}")
    public Shop queryById(@PathVariable("id") String id);

}
