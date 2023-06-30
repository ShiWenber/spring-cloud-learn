package edu.ynu.ecs.controllers;


import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.ShopItem;
import edu.ynu.ecs.service.ShopItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
/**
 * 控制层
 *
 * @author shiwenbo
 * @since 2023-01-02 00:17:05
 */
@Slf4j
@RestController
@Tag(name = "ShopItemController")
@RequestMapping("/shopItem")
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域设置，默认允许全部访问
public class ShopItemController extends AbstractTypedController<ShopItem, String> {
    
    @Resource
	private ShopItemService shopItemService;
    
    ShopItemController(ShopItemService service) {
        this.svcContext = service;
        this.shopItemService = service;
    }
}

