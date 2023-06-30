package edu.ynu.ecs.dao;


import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.entities.LineItem;
import edu.ynu.ecs.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemDao extends AbstractDao<LineItem, String> {
    /**
     * 根据订单编号查询订单详情
     * @param id
     * @return
     */
    public LineItem findLineItemById(String id);

	LineItem findByOrderAndItem(Order order, Item item);
}
