package edu.ynu.ecs.dao;
    
    


import edu.ynu.ecs.dao.AbstractDao;
import java.lang.String;
import java.util.List;

import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.entities.ShopItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 持久层
 *
 * @author shiwenbo
 * @since 2023-01-02 00:17:05
 */
@Repository
public interface ShopItemDao extends AbstractDao<ShopItem, String> {
    //    shopItem inner join item on shopItem.item_id = item.id where shopItem.shop_id = ?1
    @Query("select i from ShopItem si inner join si.item i where si.shop.id = ?1")
    List<Item> findAllItemByShopId(String id);

}

