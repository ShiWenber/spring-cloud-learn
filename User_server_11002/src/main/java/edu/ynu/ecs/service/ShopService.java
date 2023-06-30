package edu.ynu.ecs.service;


import edu.ynu.ecs.dao.AbstractDao;
import edu.ynu.ecs.dao.ShopDao;
import edu.ynu.ecs.dao.ShopItemDao;
import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.entities.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;
/**
 * 业务层
 *
 * @author shiwenbo
 * @since 2023-01-08 23:49:10
 */
@Slf4j
@Service
public class ShopService extends AbstractTypedService<Shop, String> {

    @Resource
    private ShopItemDao shopItemDao;

    @Resource
    private ShopDao shopDao;

    public ShopService(ShopDao dao,
                       @Qualifier("shopItemDao") AbstractDao shopItemDao) {
        this.dataContext = dao;
        this.shopDao = dao;
    }

    public Page<Shop> queryShopByPage(Pageable page) {
        return shopDao.queryPage(page, null);
    }



    public List<Item> getItemList(String id) {
////        先取 shopItemlist
//        Shop shop = shopDao.findById(id).get();
//        List<ShopItem> shopItems = shop.getShopItems();
//        List<Item> items = new ArrayList<>();
//        for (ShopItem shopItem : shopItems) {
//            if (!items.contains(shopItem.getItem())) {
//                items.add(shopItem.getItem());
//            }
//        }
//        return items;
        return shopItemDao.findAllItemByShopId(id);
    }
}

