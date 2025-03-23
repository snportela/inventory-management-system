package com.snportela.inventory_system.domain.entities;

import com.snportela.inventory_system.domain.OrderStatus;
import com.snportela.inventory_system.domain.PaymentStatus;
import com.snportela.inventory_system.domain.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_adress_id", nullable = false)
    private CustomerAdress customerAdress;

    @Enumerated(STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "expected_date", nullable = false)
    private LocalDateTime expectedDate;

    @Column(name = "actual_date")
    private LocalDateTime actualDate;

    @Enumerated(STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @Enumerated(STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "total_price", scale = 10, precision = 2, nullable = false)
    private BigDecimal totalPrice;

    @Generated(GenerationTime.INSERT)
    @Column(name = "tracking_number", insertable = false)
    private String trackingNumber;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

}
