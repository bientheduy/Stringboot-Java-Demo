package net.bientheduy.shop.shopmodel;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favourites")

public class Favourite {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne @JoinColumn(name = "account_id")
    Account account;
    @ManyToOne @JoinColumn(name = "product_id")
    Product product;

}
