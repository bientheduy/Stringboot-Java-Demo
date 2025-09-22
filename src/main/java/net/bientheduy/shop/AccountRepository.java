package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Account;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Integer> {
   public Account findByEmail(String email);
    
} 
