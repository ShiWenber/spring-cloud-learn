package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends AbstractDao<User, String> {

    User findByUsername(String username);

	User findByEmail(String email);

	User findByTel(String tel);

	User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);

}
