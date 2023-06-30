package edu.ynu.ecs.security.JWT.manager;


import edu.ynu.ecs.entities.User;

/**
 * 用于管理jwt凭证的认证管理器，可以实现为内存或redis
 */
public interface AuthenticationManager {

	void login(User userDetails);

	void logout(User userDetails);

	boolean validate(User userDetails);

	void logoutAll(User userDetails);

}
