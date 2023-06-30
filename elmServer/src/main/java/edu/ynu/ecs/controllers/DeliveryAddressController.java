package edu.ynu.ecs.controllers;

import edu.ynu.ecs.controllers.abstractTypedController.AbstractTypedController;
import edu.ynu.ecs.entities.DeliveryAddress;
import edu.ynu.ecs.service.DeliveryAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/deliveryAddress")
@Tag(name = "地址控制器 V1", description = "地址相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeliveryAddressController extends AbstractTypedController<DeliveryAddress, String> {
    @Resource
    DeliveryAddressService deliveryAddressService;

    DeliveryAddressController(DeliveryAddressService service) {
        this.svcContext = service;
        this.deliveryAddressService = service;
    }

}
