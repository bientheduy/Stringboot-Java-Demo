package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer>{

    
}
