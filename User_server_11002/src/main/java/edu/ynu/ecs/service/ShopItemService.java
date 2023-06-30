package edu.ynu.ecs.service;


import edu.ynu.ecs.dao.ShopItemDao;
import edu.ynu.ecs.entities.ShopItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
/**
 * 业务层
 *
 * @author shiwenbo
 * @since 2023-01-02 00:17:05
 */
@Slf4j
@Service
public class ShopItemService extends AbstractTypedService<ShopItem, String> {

	@Resource
    private ShopItemDao shopItemDao;

    public ShopItemService(ShopItemDao dao) {
        this.dataContext = dao;
        this.shopItemDao = dao;
    }

    public Page<ShopItem> queryShopItemByPage(Pageable page) {
        return shopItemDao.queryPage(page, null);
    }

}

