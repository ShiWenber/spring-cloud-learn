package edu.ynu.ecs.controllers;

import edu.ynu.ecs.entities.User;
import edu.ynu.ecs.entities.dto.LoginCommand;
import edu.ynu.ecs.security.JWT.token.RequestAuthenticationToken;
import edu.ynu.ecs.security.JWT.token.TokenType;
import edu.ynu.ecs.security.config.SecurityContants;
import edu.ynu.ecs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;


@Tag(name = "认证API V1")
@RestController
@RequestMapping(SecurityContants.AuthUrl)
//
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600) // 跨域设置，默认允许全部访问
public class AuthController {

	@Resource
	private UserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    @Operation(summary = "登录接口")
    @PostMapping("/auth-token")
    public void auth(@RequestBody @Valid LoginCommand command) throws IOException, ServletException {

        TokenType tokenType = ObjectUtils.isEmpty(command.getTokenType()) ? TokenType.INFO
                : TokenType.valueOf(command.getTokenType().toUpperCase());

        auth(command.getUsername(), command.getPassword(), tokenType);
    }


    private void auth(String username, String password, TokenType tokenType)
            throws IOException, ServletException {
        RequestAuthenticationToken token = new RequestAuthenticationToken(username, password, tokenType);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            successHandler.onAuthenticationSuccess(request, response, authentication);
			// 这里token将在body中以字符串格式返回
        } catch (AuthenticationException e) {
            failureHandler.onAuthenticationFailure(request, response, e);
        } catch (Exception e) {
            log.error("principal[{}] login fail, reason: ", token.getPrincipal(), e);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
    }

	@Operation(summary = "根据用户名查询用户实体", security = @SecurityRequirement(name = "Authorization"))
	@GetMapping("/queryByName")
	public User queryByName(@RequestParam String username) {
		return userService.getUserByUserName(username);
	}

}
