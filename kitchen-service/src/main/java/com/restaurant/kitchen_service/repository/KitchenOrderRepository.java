package com.restaurant.kitchen_service.repository;

import com.restaurant.kitchen_service.model.KitchenOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenOrderRepository extends JpaRepository<KitchenOrder, Long> {
    
    // Find orders by specific priority
    List<KitchenOrder> findByPriority(Integer priority);
    
    // Find orders with the highest priority (descending order)
    List<KitchenOrder> findAllByOrderByPriorityDesc();
    
    // Find the first order with the highest priority
    Optional<KitchenOrder> findFirstByOrderByPriorityDesc();
   
    @Query("SELECT MAX(k.priority) FROM KitchenOrder k")
    Optional<Integer> findMaxPriority();
}