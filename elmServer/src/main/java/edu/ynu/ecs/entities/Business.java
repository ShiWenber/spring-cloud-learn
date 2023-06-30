package edu.ynu.ecs.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 如果希望使用user进行注册则不可有@NotNull注解，因为user没有business类的各种字段，插入会报错
 */

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@DiscriminatorValue("2")
public class Business extends User {

	@Size(max = 40)
	@Column(name = "name", nullable = false, length = 40)
	@Schema(description = "商家名称")
	@ColumnDefault("''")
	private String name;

	@Size(max = 40)
	@Column(name = "`explain`", length = 40)
	@Schema(description = "商家简介")
	private String explain;

	@OneToMany(mappedBy = "business",
		cascade = CascadeType.ALL,
		orphanRemoval = true,
		fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = "business")
	private List<Item> items;

	//如果添加orphanRemoval = true，则表示将删除孤儿实体
//	因为shops中也有business对象，要防止json序列化器不断深入对象的内部，用IgnoreJsonProperties来去除
	@OneToMany(mappedBy = "business",
		fetch = FetchType.LAZY,
		orphanRemoval = true,
		cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "business")
	private List<Shop> shops;

	public Boolean hasShop(Shop shop) {
		return this.shops.contains(shop);
	}

	public Boolean hasItem(Item item) {
		return this.items.contains(item);
	}


	public Shop createShop(Shop shop) {
		if (!hasShop(shop)) {
			this.shops.add(shop);
			shop.setBusiness(this);
		}
		return shop;
	}

	public Item createItem(Item item) {
		if (!hasItem(item)) {
			this.items.add(item);
			item.setBusiness(this);
		}
		return item;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Business business = (Business) o;
		return id != null && Objects.equals(id, business.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
