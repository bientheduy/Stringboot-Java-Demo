package net.bientheduy.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import net.bientheduy.shop.shopmodel.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {
    public List<Category> findByActive(Boolean active, Sort sort);
    public Category findByActiveAndSlug(Boolean active, String slug);
}
