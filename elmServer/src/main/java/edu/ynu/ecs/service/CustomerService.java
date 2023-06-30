package edu.ynu.ecs.service;


import edu.ynu.ecs.dao.*;
import edu.ynu.ecs.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 业务层
 *
 * @author shiwenbo
 * @since 2022-12-24 13:20:25
 */
@Slf4j
@Service
public class CustomerService extends AbstractTypedService<Customer, String> {
	private final LineItemDao lineItemDao;
	private final ShopDao shopDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	ItemDao itemDao;

	@Resource
	BusinessDao businessDao;

	@Resource
	OrderDao orderDao;



	public CustomerService(CustomerDao dao,
						   ShopDao shopDao,
						   LineItemDao lineItemDao) {
		this.dataContext = dao;
		this.customerDao = dao;
		this.shopDao = shopDao;
		this.lineItemDao = lineItemDao;
	}


	public List<Customer> queryAll() {
		List<Customer> lst = null;
		try {
			lst = ((CustomerDao) this.dataContext).findAll();
			log.info("find customer: " + lst.size());
		} catch (Exception e) {
			log.warn(e.toString());
		}
		return lst;
	}

	public Customer changeName(String username, String password, String newName) {
		Customer customer = customerDao.findByUsernameAndPassword(username, password);
		if (customer != null) {
			customer.setUsername(newName);
			customerDao.save(customer);
			return customer;
		} else {
			return null;
		}
	}


	public Page<Customer> queryUserByPage(Pageable pageable) {
		return customerDao.queryPage(pageable, null);
	}


	public Page<Item> menu(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return itemDao.queryPage(pageable, null);
	}

	public Page<Business> businessList(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return businessDao.queryPage(pageable, null);
	}



	public LineItem addLineItem(String userId, String orderId, String itemId, int quantity) {
		LineItem lineItem = new LineItem();
		lineItem.setId(userId);
		lineItem.setOrder(orderDao.findById(orderId).get());
		lineItem.setItem(itemDao.findById(itemId).get());
		lineItem.setQuantity(quantity);
		return lineItem;
	}

	public Customer getUserById(String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}
		return customerDao.findById(id).orElse(null);
	}


	public Customer changePassword(String username, String password, String newPassword) {
		Customer customer = customerDao.findByUsername(username);
		if (customer == null) {
			return null;
		}
		customer.setPassword(newPassword);
		return customerDao.save(customer);
	}

	public Customer getByUsername(String username) {
		return customerDao.findByUsername(username);
	}


	public LineItem selectItem(String customerId, String shopId, String itemId, int quantity) {
//		连接三个表
		Customer customer = customerDao.getReferenceById(customerId);
		Shop shop = shopDao.getReferenceById(shopId);
		Order order = customer.createOrder(shop);
		// 连接customer和shop
		orderDao.save(order);
		// 连接order和item
		Item item = itemDao.getReferenceById(itemId);
		LineItem lineItem = order.addItem(item, quantity);
		lineItemDao.save(lineItem);
		return lineItemDao.save(lineItem);
	}

	public List<LineItem> lineItemList(String orderId) {
		Order order = orderDao.getReferenceById(orderId);
		return order.getLineItems();
	}

	public LineItem deleteLineItem(String id, String orderId, String itemId) {
		Order order = orderDao.getReferenceById(orderId);
		Item item = itemDao.getReferenceById(itemId);
		LineItem lineItem = lineItemDao.findByOrderAndItem(order, item);
		lineItemDao.delete(lineItem);
		return lineItem;
	}

	public List<Order> orderList(String customerId) {
		Customer customer = customerDao.getReferenceById(customerId);
		return customer.getOrders();
	}

	public List<Order> orderList(String customerId, String orderId) {
		Customer customer = customerDao.getReferenceById(customerId);
		Order order = orderDao.getReferenceById(orderId);
		return customer.getOrders();
	}

    public List<DeliveryAddress> deliveryAddressList(String id) {
		Customer customer = customerDao.getReferenceById(id);
		return customer.getDeliveryAddresses();
    }


	public DeliveryAddress createDeliveryAddress(String id, DeliveryAddress deliveryAddress) {
		Customer customer = customerDao.getReferenceById(id);
//		shop - business 多对一，先存单方，或者先构建关联后的对象，能级联保存
		deliveryAddress.setCustomer(customer);
		customer.getDeliveryAddresses().add(deliveryAddress);
		customerDao.save(customer);
		return deliveryAddress;
	}

	public DeliveryAddress deleteDeliveryAddress(String id, String addressId) {
		Customer customer = customerDao.getReferenceById(id);
		DeliveryAddress deliveryAddress = customer.getDeliveryAddresses().stream()
				.filter(da -> da.getId().equals(addressId))
				.findFirst().orElse(null);
		if (deliveryAddress != null) {
			customer.getDeliveryAddresses().remove(deliveryAddress);
			customerDao.save(customer);
		}
		return deliveryAddress;
	}

	public DeliveryAddress changeDeliveryAddress(String id, String addressId, String address) {
		Customer customer = customerDao.getReferenceById(id);
		DeliveryAddress deliveryAddress = customer.getDeliveryAddresses().stream()
				.filter(da -> da.getId().equals(addressId))
				.findFirst().orElse(null);
		if (deliveryAddress != null) {
			deliveryAddress.setAddress(address);
			customerDao.save(customer);
		}
		return deliveryAddress;
	}
}

