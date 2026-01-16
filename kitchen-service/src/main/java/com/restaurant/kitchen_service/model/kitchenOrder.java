package com.restaurant.kitchen_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "kitchen_orders")
public class KitchenOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @ElementCollection
    @CollectionTable(
            name = "kitchen_order_items",
            joinColumns = @JoinColumn(name = "kitchen_order_id")
    )
    @Column(name = "item")
    private List<String> items;

    @Column(nullable = false)
    private Integer priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    public enum Status {
        PENDING, QUEUED, COOKING, DONE
    }

    protected KitchenOrder() {}

    public KitchenOrder(Long orderId, List<String> items, Integer priority) {
        this.orderId = orderId;
        this.items = items;
        this.priority = priority;
        this.status = Status.PENDING;
    }

    // Getters and Setters
    public Long getId() { return id; }
    
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
    
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}