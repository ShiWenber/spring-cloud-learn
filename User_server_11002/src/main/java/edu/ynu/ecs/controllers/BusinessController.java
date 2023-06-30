package edu.ynu.ecs.controllers;

import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.*;
import edu.ynu.ecs.feign.ItemFeignService;
import edu.ynu.ecs.service.BusinessService;
import edu.ynu.ecs.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import java.util.List;

@Slf4j
@Tag(name = "商家控制器 V1")
@RestController
@RequestMapping("/business")
@LoadBalancerClient(name = "item-server", configuration = ItemFeignService.class)
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusinessController extends AbstractTypedController<Business, String> {

//	@Resource
//	ItemService itemService;


    @Autowired
    ItemFeignService itemFeignService;

    @Resource
    BusinessService businessService;

    BusinessController(BusinessService service) {
        this.svcContext = service;
        this.businessService = service;
    }

    @Operation(summary = "商家修改密码方法", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @Parameter(name = "name", description = "姓名", required = true)
    @Parameter(name = "password", description = "密码", required = true)
    @Parameter(name = "newPassword", description = "新密码", required = true)
    @PutMapping("/changePassword")
    public Business changePassword(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "newPassword") String newPassword) throws Exception {
        return businessService.changePassword(name, password, newPassword);
    }

    @Operation(summary = "商家查看商品列表方法",
            security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "businessId", description = "商家id", required = true)
    @GetMapping("/listItem/{businessId}")
    public List<Item> listItem(@PathVariable(value = "businessId") String businessId) {
//        先找到该商家的所有商店
        List<Shop> shopList = businessService.listShop(businessId);
        List<String> shopIdList = shopList.stream().map(Shop::getId).toList();
        List<Item> ItemList = shopIdList.stream().map(itemFeignService::listItemByShopId).toList().stream().flatMap(List::stream).toList();
//        return itemFeignService.listItemByShopId(shopIdList);
        return ItemList;
    }

    @Operation(summary = "商家查找商品方法", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @Parameter(name = "businessId", description = "商家id", required = true)
    @Parameter(name = "itemName", description = "商品名称", required = true)
    @GetMapping("/searchItem")
    public List<Item> searchItem(@RequestParam(value = "businessId") String businessId, @RequestParam(value = "itemName") String itemName) {
        return businessService.searchItem(businessId, itemName);
    }

    @Operation(summary = "商家添加商品方法", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @PostMapping("/addItem/{businessId}")
    public Item addItem(@PathVariable String businessId, @RequestBody @Valid Item item) {
        return businessService.addItem(businessId, item);
    }

    @Operation(summary = "商家创建门店", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @PostMapping("/createShop/{businessId}")
    public Shop createShop(@PathVariable String businessId, @RequestBody @Valid Shop shop) {
        return businessService.createShop(businessId, shop);
    }

    @Operation(summary = "商家上架商品", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @PostMapping("/putOnShelf/{businessId}/{itemId}/{shopId}")
    public ShopItem putOnShelf(@PathVariable String businessId, @PathVariable String itemId, @PathVariable String shopId) {
        return businessService.setOnShelf(businessId, itemId, shopId);
    }

    @Operation(summary = "商家创建商品", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @PostMapping("/createItem/{businessId}")
    public ShopItem createItem(@PathVariable String businessId, @RequestBody @Valid Item item) {
        return businessService.createItem(businessId, item);
    }

    @Operation(summary = "商家查看门店订单", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @PostMapping("/listShopOrder/{businessId}/{shopId}")
    public List<Order> listShopOrder(@PathVariable String businessId, @PathVariable String shopId) {
        return businessService.listShopOrder(businessId, shopId);
    }

    @Operation(summary = "商家查看门店", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @GetMapping("/listShop/{businessId}")
    public List<Shop> listShop(@PathVariable String businessId) {
        return businessService.listShop(businessId);
    }

    @Operation(summary = "商家确定订单", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @GetMapping("/confirmOrder/{businessId}/{orderId}")
    public Order confirmOrder(@PathVariable String businessId, @PathVariable String orderId) {
        return businessService.confirmOrder(businessId, orderId);
    }

    @Operation(summary = "商家取消订单(退款)", security = {
            @SecurityRequirement(name = "Authorization")
    })
    @GetMapping("/cancelOrder/{businessId}/{orderId}")
    public Order cancelOrder(@PathVariable String businessId, @PathVariable String orderId) {
        return businessService.cancelOrder(businessId, orderId);
    }


}
