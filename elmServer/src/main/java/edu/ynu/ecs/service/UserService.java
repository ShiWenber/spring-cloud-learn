package edu.ynu.ecs.service;


import edu.ynu.ecs.dao.*;
import edu.ynu.ecs.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 业务层
 *
 * @author shiwenbo
 * @since 2022-12-24 13:20:25
 */
@Slf4j
@Service
public class UserService extends AbstractTypedService<User, String> {

	@Resource
    private UserDao userDao;

	@Resource
	ItemDao itemDao;

	@Resource
	BusinessDao businessDao;

	@Resource
	OrderDao orderDao;



	public UserService(UserDao dao) {
        this.dataContext = dao;
        this.userDao = dao;
    }


	public List<User> queryAll() {
		List<User> lst = null;
		try {
			lst = ((UserDao) this.dataContext).findAll();
			log.info("find user: " + lst.size());
		} catch (Exception e) {
			log.warn(e.toString());
		}
		return lst;
	}

	public User changeName(String username, String password, String newName) {
		User user = userDao.findUserByUsernameAndPassword(username, password);
		if (user != null) {
			user.setUsername(newName);
			userDao.save(user);
			return user;
		} else {
			return null;
		}
	}


	public Page<User> queryUserByPage(Pageable pageable) {
		return userDao.queryPage(pageable, null);
	}

	// 登陆相关
	public User getUserById(String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}
		return userDao.findById(id).orElse(null);
	}

	public User getUserByUserName(String username) {
		return userDao.findByUsername(username);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User findUserByTel(String tel) {
		return userDao.findByTel(tel);
	}

	public User changePassword(String username, String password, String newPassword) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			return null;
		}
		user.setPassword(newPassword);
		return userDao.save(user);
	}

	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}
}

