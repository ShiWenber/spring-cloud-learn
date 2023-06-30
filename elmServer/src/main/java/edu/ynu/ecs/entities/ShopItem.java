package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class ShopItem extends AbstractDomainEntity{
	@Column(name = "status")
	@ColumnDefault("\"shelf\"") // 设置列的默认值
	@Schema(description = "状态：未上架off, 上架on")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`shop$id`", nullable = false)
	@Schema(description = "shop")
	@JsonIgnore
	private Shop shop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`item$id`", nullable = false)
	@Schema(description = "item")
	@JsonIgnore
	private Item item;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ShopItem shopItem = (ShopItem) o;
		return id != null && Objects.equals(id, shopItem.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
