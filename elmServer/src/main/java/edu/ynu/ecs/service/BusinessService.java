package edu.ynu.ecs.service;

import edu.ynu.ecs.dao.BusinessDao;
import edu.ynu.ecs.dao.ItemDao;
import edu.ynu.ecs.dao.OrderDao;
import edu.ynu.ecs.dao.ShopDao;
import edu.ynu.ecs.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BusinessService extends AbstractTypedService<Business, String> {

	@Resource
	ShopDao shopDao;
	//    如果提供给controller调用的话就不用resource装配，而是在controller中装配，传参给新建的BusinessService
//    @Resource spring boot 3.0 标准，使用构造函数注入
	@Resource
    BusinessDao businessDao;

	@Resource
	OrderDao orderDao;
    // TODO: 更换为构造函数注入
    @Resource
    ItemDao itemDao;

    public BusinessService(BusinessDao dao,
						   ShopDao shopDao) {
        this.dataContext = dao;
        this.businessDao = dao;
		this.shopDao = shopDao;
	}

    public List<Business> queryAll() {
        List<Business> lst = null;
        try {
//            使用父类的dataContext统一的方法
            lst = ((BusinessDao) this.dataContext).findAll();
            log.info("find business: " + lst.size());
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return lst;
    }

    public Business login(String name, String password) {
        return businessDao.findBusinessByNameAndPassword(name, password);
    }

    public Business register(String name, String password, String img, Integer orderTypeId) {
        // 查询表中是否已经存在该name
        Business business = businessDao.findBusinessByName(name);
        if (business != null) {
            return null;
        } else {
            business = new Business();
            business.setName(name);
            business.setPassword(password);
            businessDao.save(business);
            return business;
        }
    }

    /**
     * 查看指定商家的所有商品
     *
     * @param businessId
     * @return
     */
    public List<Item> listItem(String businessId) {
		Business business = businessDao.getReferenceById(businessId);
        return business.getItems();
    }

    /**
     * 根据商品名称查询商品
     * @param businessId
     * @param itemName
     * @return
     */
    public List<Item> searchItem(String businessId, String itemName) {
		Business business = businessDao.getReferenceById(businessId);
//		使用stream流的filter方法过滤结果
        return business.getItems().stream().filter(item -> item.getName().contains(itemName)).collect(Collectors.toList());
    }

    public Item addItem(String businessId, Item item) {
		Business business = businessDao.getReferenceById(businessId);
		item.setBusiness(business);
        itemDao.save(item);
        return item;
    }

    public Business changePassword(String name, String oldPassword, String newPassword) {
        Business business = businessDao.findBusinessByNameAndPassword(name, oldPassword);
        if (business != null) {
            business.setPassword(newPassword);
//            未使用自增id而是uuid,save的时候id也不会变 TODO:
            businessDao.save(business);
            return business;
        } else {
            return null;
        }
    }


    public Page<Business> queryBusinessByPage(Pageable pageable) {
        return businessDao.queryPage(pageable, null);
    }

	public Business getByUsername(String username) {
		return businessDao.findByUsername(username);
	}

	public Shop createShop(String bid, Shop shop) {
		Business business = businessDao.getReferenceById(bid);
//		shop - business 多对一，先存单方，或者先构建关联后的对象，能级联保存
		shop.setBusiness(business);
		business.getShops().add(shop);
		businessDao.save(business);
		return shop;
	}

	public ShopItem setOnShelf(String businessId, String itemId, String shopId) {
		Business business = businessDao.getReferenceById(businessId);
		Item item = itemDao.getReferenceById(itemId);
		Shop shop = shopDao.getReferenceById(shopId);
		ShopItem shopItem = new ShopItem();
		shopItem.setItem(item);
		shopItem.setShop(shop);
		shop.getShopItems().add(shopItem);
		businessDao.save(business);
		return shopItem;
	}

	public ShopItem createItem(String businessId, Item item) {
		Business business = businessDao.getReferenceById(businessId);
		item.setBusiness(business);
		itemDao.save(item);
		ShopItem shopItem = new ShopItem();
		shopItem.setItem(item);
		shopItem.setShop(business.getShops().get(0));
		shopDao.save(business.getShops().get(0));
		return shopItem;
	}


	public List<Order> listShopOrder(String businessId, String shopId) {
		Business business = businessDao.getReferenceById(businessId);
		Shop shop = shopDao.getReferenceById(shopId);
		return shop.getOrders();
	}

	public List<Shop> listShop(String businessId) {
		Business business = businessDao.getReferenceById(businessId);
		return business.getShops();
	}

	public Order confirmOrder(String businessId, String orderId) {
		Business business = businessDao.getReferenceById(businessId);
		Order order = orderDao.getReferenceById(orderId);
		order.setStatus("confirmed");
		orderDao.save(order);
		return order;
	}

	public Order cancelOrder(String businessId, String orderId) {
		Business business = businessDao.getReferenceById(businessId);
		Order order = orderDao.getReferenceById(orderId);
		order.setStatus("canceled");
		orderDao.save(order);
		return order;
	}
}
