package edu.ynu.ecs.controllers;

import edu.ynu.ecs.entities.User;
import edu.ynu.ecs.entities.dto.LoginCommand;
import edu.ynu.ecs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Tag(name = "认证API V1")
@RestController
@RequestMapping("/pub/auth")
//
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域设置，默认允许全部访问
public class AuthController {

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    @Operation(summary = "登录接口")
    @PostMapping("/auth-token")
    public User auth(@RequestBody @Valid LoginCommand command) throws IOException, ServletException {

        User user = userService.findUserByUsername(command.getUsername());
//        if (user == null) {
//            response.sendError(HttpStatus.UNAUTHORIZED.value(), "password or username is invalid");
//        } else {
////            将user 转为json
//            response.getWriter().write("Bearer " + "none");
//        }
        user.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJyYW5kTnVtIjoiOWQwNGY1MzQtMzRlNS00YmJiLTgzOWItMTFlNjBkMjQ5NzU1IiwidXNlcklkIjoiNDAyODgxZTY4OGQ4YmM1ZDAxODhkYmFhMzllYTAwMDgiLCJ1c2VybmFtZSI6IjE5OTg2NzYwMDAzIn0.gw571rTqui3BjlqbaqXQS8gMACvRh_NPLtvg1jdeTJc");
        return user;
    }

    @Operation(summary = "根据用户名查询用户实体", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/queryByName")
    public User queryByName(@RequestParam String username) {
        return userService.getUserByUserName(username);
    }

}
