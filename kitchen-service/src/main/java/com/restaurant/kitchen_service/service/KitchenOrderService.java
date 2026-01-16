package com.restaurant.kitchen_service.service;

import com.restaurant.kitchen_service.model.KitchenOrder;
import com.restaurant.kitchen_service.repository.KitchenOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KitchenOrderService {

    @Autowired
    private KitchenOrderRepository repository;

    public KitchenOrder save(KitchenOrder order) {
        Integer maxPriority = repository.findMaxPriority().orElse(0);
        order.setPriority(maxPriority + 1);
        return repository.save(order);
    }

    public List<KitchenOrder> findAll() {
        return repository.findAll();
    }

    public KitchenOrder updateStatus(Long id, KitchenOrder.Status status) {
        KitchenOrder order = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        
        order.setStatus(status);
        
        if (status == KitchenOrder.Status.COOKING && order.getStartedAt() == null) {
            order.setStartedAt(LocalDateTime.now());
        }
        
        if (status == KitchenOrder.Status.DONE) {
            order.setCompletedAt(LocalDateTime.now());
        }
        
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}