package net.bientheduy.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bientheduy.shop.shopmodel.Address;

public interface AddressRepository extends JpaRepository<Address , Integer> {

    
}
