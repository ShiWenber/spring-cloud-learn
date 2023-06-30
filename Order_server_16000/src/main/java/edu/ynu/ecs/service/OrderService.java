package edu.ynu.ecs.service;

import edu.ynu.ecs.dao.OrderDao;
import edu.ynu.ecs.entities.Customer;
import edu.ynu.ecs.entities.Order;
import edu.ynu.ecs.entities.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class OrderService extends AbstractTypedService<Order, String> {
    @Resource
    OrderDao orderDao;

    public OrderService(OrderDao dao) {
        this.dataContext = dao;
        this.orderDao = dao;
    }

    public List<Order> queryAll() {
        List<Order> lst = null;
        try {
            lst = ((OrderDao) this.dataContext).findAll();
            log.info("find order: " + lst.size());
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return lst;
    }

    public Page<Order> queryOrderByPage(Pageable pageable) {
        return orderDao.queryPage(pageable, null);
    }

    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    public Order findByCustomerAndShop(Customer c, Shop s) {
        return orderDao.findByCustomerAndShop(c,s);
    }
}
