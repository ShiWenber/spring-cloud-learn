package edu.ynu.ecs.controllers;


import edu.ynu.ecs.entities.Business;
import edu.ynu.ecs.entities.Customer;
import edu.ynu.ecs.entities.User;
//import edu.ynu.ecs.security.config.SecurityContants;
import edu.ynu.ecs.service.BusinessService;
import edu.ynu.ecs.service.CustomerService;
import edu.ynu.ecs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 控制层
 *
 * @author shiwenbo
 * @since 2022-12-24 13:20:25
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域设置，默认允许全部访问
@Tag(name = "注册API V1")
@RequestMapping("/pub/auth")
public class registerController {


	@Resource
	private UserService userService;

	@Resource
	private BusinessService businessService;

	@Resource
	private CustomerService customerService;

	@Resource
	private HttpServletResponse request;

	@Resource
	private HttpServletResponse response;

	@Operation(summary = "注册")
	@PostMapping("/register")
	public void register(@RequestBody @Valid User user) {
		log.info("register");
		try {
//			当出现同名注册时，这个地方会报错
			if (userService.getUserByUserName(user.getUsername()) != null) {
				request.setStatus(HttpStatus.BAD_REQUEST.value());
				response.getWriter().write("username already exists");
				log.warn("username already exists");
			} else {
				userService.create(user);
				request.setStatus(HttpStatus.OK.value());
				response.getWriter().write("register success");
				log.warn("register success");
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			log.warn("register failde", e);
		}
	}


	@Operation(summary = "顾客注册")
	@PostMapping("/registerCustomer")
	public void registerCustomer(@RequestBody @Valid Customer customer) {
		log.info("registerCustomer");
		try {
			if (userService.getUserByUserName(customer.getUsername()) != null) {
				request.setStatus(HttpStatus.BAD_REQUEST.value());
				response.getWriter().write("username already exists");
				log.warn("username already exists");
			} else {
				customerService.create(customer);
				request.setStatus(HttpStatus.OK.value());
				response.getWriter().write("register success");
				log.warn("register success");
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			log.warn("register failde", e);
		}
	}
	@Operation(summary = "商家注册")
	@PostMapping("/registerBusiness")
	public void registerBusiness(@RequestBody @Valid Business business) {
		log.info("registerBusiness");
		try {
			if (userService.getUserByUserName(business.getUsername()) != null) {
				request.setStatus(HttpStatus.BAD_REQUEST.value());
				response.getWriter().write("username already exists");
				log.warn("username already exists");
			} else {
				businessService.create(business);
				request.setStatus(HttpStatus.OK.value());
				response.getWriter().write("register success");
				log.warn("register success");
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			log.warn("register failde", e);
		}
	}

}