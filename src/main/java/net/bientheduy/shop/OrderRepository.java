package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
