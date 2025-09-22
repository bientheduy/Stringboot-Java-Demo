package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
