package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    
}
