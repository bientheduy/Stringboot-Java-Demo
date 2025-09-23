package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByActive(Boolean active);
}
