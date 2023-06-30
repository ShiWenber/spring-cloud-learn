package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
//@Builder
@AllArgsConstructor
@Entity
@DiscriminatorValue("1")
public class Customer extends User {

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "customer")
    private List<DeliveryAddress> deliveryAddresses;

    public Boolean hasOrder(Order order) {
        return this.orders.contains(order);
    }

    public Order createOrder(Shop shop) {
        Order order = new Order();
        order.setCustomer(this);
        order.setShop(shop);
        this.orders.add(order);
        return order;
    }

    public Order createOrder(Order order) {
        if (this.hasOrder(order)) {
            return null;
        }
        order.setStatus("notPaid");
        this.orders.add(order);
        return order;
    }

    public Order pay(Order order) {
        if (!this.hasOrder(order)) {
            return null;
        }
        order.setStatus("paid");
        return order;
    }

    public Order cancel(Order order) {
        if (!this.hasOrder(order)) {
            return null;
        }
        order.setStatus("canceled");
        return order;
    }

    public DeliveryAddress createDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (this.deliveryAddresses.contains(deliveryAddress)) {
            return null;
        }
        deliveryAddress.setCustomer(this);
        return deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer that = (Customer) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
