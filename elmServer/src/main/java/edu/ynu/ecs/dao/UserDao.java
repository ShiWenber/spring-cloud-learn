package edu.ynu.ecs.dao;

import org.springframework.stereotype.Repository;
import edu.ynu.ecs.entities.User;

@Repository
public interface UserDao extends AbstractDao<User, String> {

    User findByUsername(String username);

	User findByEmail(String email);

	User findByTel(String tel);

	User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);

}
