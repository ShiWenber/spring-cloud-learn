package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Shop extends AbstractDomainEntity{
	
	@Size(max = 20)
	@Column(name = "name", nullable = true, length = 20)
	@Schema(description = "商店名称")
	private String name;

	@Column(name = "description", nullable = true)
	@Schema(description = "商店描述")
	private String description;

//	数据库中使用LONGTEXT类型存储图片
	@Lob
	@Column(name = "image", nullable = true)
	@Schema(description = "商店图片base64字符串")
	private String image;

	@Column(name = "startPrice", nullable = true)
	@Schema(description = "起送价格")
	private Double startPrice;

	@Column(name = "deliveryPrice", nullable = true)
	@Schema(description = "配送价格")
	private Double deliveryPrice;

	@OneToMany(mappedBy = "shop",
		fetch = FetchType.LAZY,
		orphanRemoval = true,
		cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "shop")
	private List<ShopItem> shopItems;

	@OneToMany(mappedBy = "shop",
		fetch = FetchType.LAZY,
		orphanRemoval = true,
		cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "shop")
	private List<Order> orders;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`business$id`", nullable = false)
	@Schema(description = "")
	@JsonIgnore
	private Business business;


	public Boolean hasItem(Item item) {
		for (ShopItem shopItem : shopItems) {
			if (shopItem.getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	public ShopItem createItem(Item item) {
		if (hasItem(item)) {
			return null;
		}
		if (!item.getBusiness().equals(business)) {
			return null;
		}
		ShopItem shopItem = new ShopItem();
		shopItem.setItem(item);
		shopItem.setShop(this);
		shopItems.add(shopItem);
		return shopItem;
	}

	/**
	 * 上架商品
	 * @param item
	 * @return ShopItem
	 */
	public ShopItem setOnSale(Item item) {
//		查找是否有该商品，如果有则上架
		for (ShopItem shopItem : shopItems) {
			if (shopItem.getItem().equals(item)) {
				shopItem.setStatus("on");
				return shopItem;
			}
		}
		return null;
	}

	public Boolean hasOrder(Order order) {
		return this.orders.contains(order);
	}

	/**
	 * 确认订单，前提是订单状态为已支付
	 * @param order
	 * @return
	 */
	public Order confirmOrder(Order order) {
		if (!hasOrder(order)) {
			return null;
		}
		order.setStatus("paid");
		return order;
	}

	/**
	 * 退款
	 * @param o
	 * @return
	 */
	public Order refund(Order o) {
		if (!hasOrder(o)) {
			return null;
		}
		o.setStatus("refunded");
		return o;
	}





	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Shop shop = (Shop) o;
		return id != null && Objects.equals(id, shop.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
