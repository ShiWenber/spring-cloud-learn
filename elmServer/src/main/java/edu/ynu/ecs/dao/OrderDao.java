package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.Customer;
import edu.ynu.ecs.entities.Order;
import edu.ynu.ecs.entities.Shop;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends AbstractDao<Order, String>{
    Order findByCustomerAndShop(Customer customer, Shop shop);
    /**
     * 根据顾客编号查询订单
     * @param id
     * @return
     */
//    List<Order> findOrderByBusiness$id(String id);

//    List<Order> findAllByUser$id(String id);
}
