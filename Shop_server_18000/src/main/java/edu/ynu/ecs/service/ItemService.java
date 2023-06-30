package edu.ynu.ecs.service;

import edu.ynu.ecs.dao.ItemDao;
import edu.ynu.ecs.entities.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ItemService extends AbstractTypedService<Item, String> {
    @Resource
    ItemDao itemDao;

    public ItemService(ItemDao dao) {
        this.dataContext = dao;
        this.itemDao = dao;
    }

    public List<Item> queryAll() {
        List<Item> lst = null;
        try {
            lst = ((ItemDao) this.dataContext).findAll();
            log.info("find item: " + lst.size());
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return lst;
    }

    public Page<Item> queryItemByPage(Pageable pageable) {
        return itemDao.queryPage(pageable, null);
    }

    public Item findItemById(String id) {
        try {
            return itemDao.findItemById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> listItemByShopId(String id) {
        return itemDao.findItemsByShop$Id(id);
    }
}
