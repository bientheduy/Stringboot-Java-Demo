package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.CartDetail;

public interface CartDetailsRepository extends JpaRepository<CartDetail , Integer> {
    
}
