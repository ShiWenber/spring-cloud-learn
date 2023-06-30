package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.Business;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessDao extends AbstractDao<Business, String> {


    /**
     * 根据商家名从数据库中查询商家
     * @param name
     * @return Business对象
     * @throws Exception
     */
    Business findBusinessByName(String name);

    Business findByUsername(String username);

	Business findBusinessByNameAndPassword(String name, String oldPassword);
}
