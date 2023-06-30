package edu.ynu.ecs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class DeliveryAddress extends AbstractDomainEntity{


    @Column(name = "contactName")
    private String contactName;
    @Column(name = "contactSex")
    private Integer contactSex;
    @Column(name = "contactTel")
    private String contactTel;
    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`customer$id`", nullable = false)
    @Schema(description = "顾客")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "deliveryAddress",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "deliveryAddress", hidden = true) // 表示在swagger中不显示该属性，一般给子对象添加，能保持文档的简洁
    private java.util.List<Order> orders;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeliveryAddress that = (DeliveryAddress) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
