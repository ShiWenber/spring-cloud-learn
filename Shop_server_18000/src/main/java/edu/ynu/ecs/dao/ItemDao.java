package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemDao extends AbstractDao<Item, String> {

    /**
     * 主键查询
     * @param itemId 主键
     * @return Item对象的List
     * @throws Exception 异常
     */
    Item findItemById(String itemId);

    /**
     * 根据菜品名称从数据库中搜索菜品
     * @param name
     * @return List<Item>
     * @throws Exception
     */
    List<Item> findItemByNameLike(String name) throws Exception;

//    使用jqpl语句查询
    @Query(value = "select i from ShopItem si inner join si.item i where si.shop.id = ?1")
    List<Item> findItemsByShop$Id(String id);
}
