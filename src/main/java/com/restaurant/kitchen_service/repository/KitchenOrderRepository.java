package com.restaurant.kitchen_service.repository;

import com.restaurant.kitchen_service.model.KitchenOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenOrderRepository extends JpaRepository<KitchenOrder, Long> {
    
    @Query("SELECT COALESCE(MAX(k.priority), 0) FROM KitchenOrder k")
    Integer findMaxPriority();
}