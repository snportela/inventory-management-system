package com.snportela.inventory_system.domain.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderItemId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;
}
