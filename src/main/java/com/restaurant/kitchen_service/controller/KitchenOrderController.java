package com.restaurant.kitchen_service.controller;

import com.restaurant.kitchen_service.model.KitchenOrder;
import com.restaurant.kitchen_service.service.KitchenOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchen/orders")
public class KitchenOrderController {

    @Autowired
    private KitchenOrderService kitchenOrderService;

    @PostMapping
    public ResponseEntity<KitchenOrder> createOrder(@RequestBody KitchenOrderRequest request) {
        KitchenOrder order = new KitchenOrder(
            request.getItems()
        );
        
        KitchenOrder savedOrder = kitchenOrderService.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<KitchenOrder>> getAllOrders() {
        return ResponseEntity.ok(kitchenOrderService.findAll());
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<KitchenOrder> updateStatus(
            @PathVariable Long id, 
            @RequestBody StatusUpdateRequest request) {
        KitchenOrder updated = kitchenOrderService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        kitchenOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

// Request DTOs
class KitchenOrderRequest {
    private List<String> items;
    
    // Getters and Setters
    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
}

class StatusUpdateRequest {
    private KitchenOrder.Status status;
    
    public KitchenOrder.Status getStatus() { return status; }
    public void setStatus(KitchenOrder.Status status) { this.status = status; }
}