//package edu.ynu.ecs.feign;
//
//import edu.ynu.ecs.entities.Item;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@FeignClient("shop-server")
//public interface ShopFeignService {
//     /**通过商店编号查看商品列表*/
//    @GetMapping("/getItemList/{id}")
//    public List<Item> getItemList(@PathVariable String id);
//}
