package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.Item;
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
    List<Item> findItemById(String itemId) throws Exception;

    /**
     * 根据商家编号从数据库中查询商家的菜品
     * @param id 商品编号
     * @param bid 商家编号
     * @return Item对象的List
     * @throws Exception 异常
     */
//    List<Item> findItemByIdAndAndBusiness$id(String id, String bid) throws Exception;

//    int saveItem(Item item) throws SQLException;

    /**
     * 根据菜品编号从数据库中删除菜品
     * @param itemId 商品编号
     * @return Item 商品
     * @throws Exception 异常
     */
//    Item getItemById(Integer itemId) throws Exception;

//    int updateItem(Item item) throws SQLException;

//    int removeItem(Integer itemId) throws SQLException;

    /**
     * 根据菜品名称从数据库中搜索菜品
     * @param name
     * @return List<Item>
     * @throws Exception
     */
    List<Item> findItemByNameLike(String name) throws Exception;

    Collection<? extends Item> findAllByIdAndNameLikeAndAndExplainLike(String id, String name, String explain);

    /**
     * 查询指定商家的所有菜品
     * @param bid
     * @return
     * @throws Exception
     */
//    List<Item> findItemByBusiness$id(String bid) throws Exception;

    /**
     * 根据商家编号和菜品名称从数据库中搜索菜品
     * @param bid
     * @param name
     * @return
     * @throws Exception
     */
//    List<Item> findItemByBusiness$idAndNameLike(String bid, String name) throws Exception;
    //TODO: findall 和 finditem的区别
//    List<Item> findAllByBusiness$idAndNameLikeAndAndExplainLike(String businessId, String name, String explain) throws Exception;
}
