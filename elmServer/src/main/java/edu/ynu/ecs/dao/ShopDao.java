package edu.ynu.ecs.dao;
    
    


import edu.ynu.ecs.dao.AbstractDao;
import java.lang.String;
import java.util.List;

import edu.ynu.ecs.entities.Item;
import edu.ynu.ecs.entities.Shop;
import org.springframework.stereotype.Repository;

/**
 * 持久层
 *
 * @author shiwenbo
 * @since 2023-01-08 23:49:10
 */
@Repository
public interface ShopDao extends AbstractDao<Shop, String> {

}

