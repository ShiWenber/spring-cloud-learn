package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends AbstractDao<Customer, String>{
	Customer findByUsernameAndPassword(String username, String password);

	Customer findByUsername(String username);
}
