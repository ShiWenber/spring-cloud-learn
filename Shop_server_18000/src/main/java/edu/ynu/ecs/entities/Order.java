package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.validation.constraints.Size;
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
@Table(name = "`Order`")
public class Order extends AbstractDomainEntity {

//    @Size(max = 32)
//    @NotNull
//    @Column(name = "`user$id`", nullable = false, length = 32)
//    @Schema(description = "顾客id")
//    private String user$id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`customer$id`", nullable = false)
	@Schema(description = "顾客")
	@JsonIgnore
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`shop$id`", nullable = false)
	@Schema(description = "")
	@JsonIgnore
	private Shop shop;

	@Size(max = 20)
    @Column(name = "status", nullable = false)
    @Schema(description = "订单状态: notPaid paid refunded canceled(逻辑删除未支付的订单) confirmed")
    private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deliveryAddress$id", nullable = false)
	@Schema(description = "deliveryAddress", hidden = true)
	@JsonIgnore
	private DeliveryAddress deliveryAddress;
	

	@OneToMany(mappedBy = "order",
		fetch = FetchType.LAZY,
		orphanRemoval = true,
		cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "order")
	private List<LineItem> lineItems;

//	订单添加商品
	public LineItem addItem(Item item, Integer quantity) {
		if (!this.shop.hasItem(item)) {
			return null;
		}
		for (LineItem lineItem : lineItems) {
			if (lineItem.getItem().equals(item)) {
				lineItem.setQuantity(lineItem.getQuantity() + quantity);
				return lineItem;
			}
		}
		LineItem lineItem = new LineItem();
		lineItem.setItem(item);
		lineItem.setOrder(this);
		lineItem.setQuantity(1);
		lineItems.add(lineItem);
		return lineItem;
	}

	public Boolean hasItem(Item item) {
		for (LineItem lineItem : lineItems) {
			if (lineItem.getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}


	public void setStatus(String status) {
//		商家确认已支付订单
		if (status.equals("confirmed")) {
			if (this.status.equals("paid")) {
				this.status = status;
			} else {
				System.out.println("订单状态错误");
				throw new RuntimeException("订单状态错误");
			}
		}
//		商家退款已支付订单
		if (status.equals("refunded")) {
			if (this.status.equals("paid")) {
				this.status = status;
			} else {
				System.out.println("订单状态错误");
				throw new RuntimeException("订单状态错误");
			}
		}
//		顾客取消未支付的单
		if (status.equals("canceled")) {
			if (this.status.equals("notPaid")) {
				this.status = status;
			} else {
				System.out.println("订单状态错误");
				throw new RuntimeException("订单状态错误");
			}
		}
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}