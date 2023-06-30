package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import jakarta.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "LineItem")
public class LineItem extends AbstractDomainEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`order$id`", nullable = false)
	@Schema(description = "order")
	@JsonIgnore
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`item$id`", nullable = false)
	@Schema(description = "")
	@JsonIgnore
	private Item item;

	@NotNull
	@Column(name = "quantity", nullable = false)
	@Schema(description = "购买数量")
	private Integer quantity;

	public Double getTotalPrice() {
		return this.item.getPrice().doubleValue() * this.quantity.doubleValue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		LineItem that = (LineItem) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}