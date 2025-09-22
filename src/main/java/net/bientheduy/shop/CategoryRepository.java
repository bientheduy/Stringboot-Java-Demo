package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {
    
}
