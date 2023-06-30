package edu.ynu.ecs.controllers;

import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.dao.AbstractDao;
import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
@Tag(name = "食物控制器 V1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController extends AbstractTypedController<Item, String> {
    @Resource
    private ItemService itemService;

    ItemController(ItemService service) {
        this.svcContext = service;
        this.itemService = service;
    }
//
//    @Operation(summary = "根据食物id获取食物")
//    @Parameter(name = "id", description = "食物id", required = true)
//    @GetMapping("/{id}")
//    public Item getItem(@PathParam("id") String id) {
//        Item res = itemService.findItemById(id);
//        return res;
//    }

    @Operation(summary = "根据商店id获取食物列表")
    @Parameter(name = "shopId", description = "商店id", required = true)
    @GetMapping("/list/page/{id}")
    public List<Item> listItemByShopId(@PathParam("id") String id) {
        return itemService.listItemByShopId(id);
    }

}
