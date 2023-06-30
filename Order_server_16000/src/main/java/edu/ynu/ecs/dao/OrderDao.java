package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.Customer;
import edu.ynu.ecs.entities.Order;
import edu.ynu.ecs.entities.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends AbstractDao<Order, String>{
    Order findByCustomerAndShop(Customer customer, Shop shop);
//    List<Order> findAllByUser$id(String id);
}
