package edu.ynu.ecs.controllers;

import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import jakarta.persistence.*;

@Slf4j
@RestController
@RequestMapping("/item")
@Tag(name = "食物控制器 V1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController extends AbstractTypedController<Item, String> {
    @Resource
    ItemService itemService;

    ItemController(ItemService service) {
        this.svcContext = service;
        this.itemService = service;
    }

    @Operation(summary = "根据食物id获取食物")
    @Parameter(name = "id", description = "食物id", required = true)
    @GetMapping("/")
    public String getItem(@PathParam("id") String id) throws Exception {
//        return itemDao.findItemById(id).toString();
        return null; // TODO
    }

    @Operation(summary = "根据商家id获取食物列表")
    @Parameter(name = "businessId", description = "商家id", required = true)
    @GetMapping("/list")
    public String listItemByBusinessId(@PathParam("id") String id) throws Exception {
//        List<Item> list = itemDao.findItemByBusiness$id(id);
//        return list.toString();
        return null; // TODO
    }

}
