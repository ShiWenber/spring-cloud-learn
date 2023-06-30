package edu.ynu.ecs.controllers;

import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.Order;
import edu.ynu.ecs.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/order")
@Tag(name = "订单控制器 V1", description = "订单相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController extends AbstractTypedController<Order, String> {
    @Resource
    OrderService orderService;

    OrderController(OrderService service) {
        this.svcContext = service;
        this.orderService = service;
    }
}
