package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Item")
public class Item extends AbstractDomainEntity {

	@Size(max = 30)
	@NotNull
	@Column(name = "name", nullable = false, length = 30)
	@Schema(description = "食物名称")
	private String name;

	@Size(max = 30)
	@NotNull
	@Column(name = "`explain`", nullable = false, length = 30)
	@Schema(description = "食物简介")
	private String explain;

	@NotNull
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	@Schema(description = "食物价格")
	private BigDecimal price;

	@Lob
	@Column(name = "image", nullable = true)
	@Schema(description = "食物图片base64字符串")
	private String image;

	@OneToMany(mappedBy = "item",
		fetch = FetchType.LAZY,
		orphanRemoval = true,
		cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "item")
	private List<ShopItem> shopItems;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`business$id`", nullable = false)
	@Schema(description = "商家")
	@JsonIgnore
	private Business business;

	@OneToMany(mappedBy = "item",
		fetch = FetchType.LAZY,
		orphanRemoval = true,
		cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "item")
	private List<LineItem> lineItems;

	public Boolean hasLineItem(LineItem lineItem) {
		return lineItems.contains(lineItem);
	}

	public Boolean hasShopItem(ShopItem shopItem) {
		return shopItems.contains(shopItem);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Item item = (Item) o;
		return id != null && Objects.equals(id, item.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}