package edu.ynu.ecs.controllers;
    
    



import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.entities.Shop;
import edu.ynu.ecs.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import jakarta.annotation.Resource;
/**
 * 控制层
 *
 * @author shiwenbo
 * @since 2023-01-08 23:49:10
 */
@Slf4j
@RestController
@Tag(name = "ShopController")
@RequestMapping("/shop")
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域设置，默认允许全部访问
public class ShopController extends AbstractTypedController<Shop, String> {
    
    @Resource
	private ShopService shopService;
    
    ShopController(ShopService service) {
        this.svcContext = service;
        this.shopService = service;
    }


    @Operation(summary = "通过商店编号查看商品列表方法", security ={
    		@SecurityRequirement(name = "Authorization")
    	})
    @GetMapping("/getItemList/{id}")
    public List<Item> getItemList(@PathVariable String id) {
        return shopService.getItemList(id);
    }
}

