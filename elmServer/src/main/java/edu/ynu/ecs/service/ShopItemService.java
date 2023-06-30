package edu.ynu.ecs.service;
    
    


import edu.ynu.ecs.service.AbstractTypedService;
import java.lang.String;
import edu.ynu.ecs.entities.ShopItem;
import edu.ynu.ecs.service.ShopItemService;
import edu.ynu.ecs.dao.ShopItemDao;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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

