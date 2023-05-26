package com.ecom.backend.order.model;

import com.ecom.backend.common.AbstractEntity;
import com.ecom.backend.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Orders")
public class Order extends AbstractEntity {
    private String orderStatus;
    private String paymentStatus;
    private Date orderDelivered;
    private double orderAmount;
    private String billingAddress;
    private Date orderCreateAt;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem=new HashSet<>();

}
