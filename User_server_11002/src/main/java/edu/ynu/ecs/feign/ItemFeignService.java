package edu.ynu.ecs.feign;

import edu.ynu.ecs.entities.Item;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "shop-server")
public interface ItemFeignService {

    /** 根据食物id获取食物信息 */
    @GetMapping("/{id}")
    Item getItem(@PathParam("id") String id);

    @GetMapping("/list/page/{id}")
    List<Item> listItemByShopId(@PathParam("id") String id);
}
