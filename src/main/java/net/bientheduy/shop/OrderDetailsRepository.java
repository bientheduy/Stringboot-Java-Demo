package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    
}
