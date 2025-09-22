package net.bientheduy.shop.shopmodel;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String email;
    String password;
    @Column(columnDefinition = "nvarchar(60)")
    String fullname;
    Boolean gender;
    Boolean admin;
    @OneToMany(mappedBy = "account")
    List<Address> addresses;
    @OneToMany(mappedBy = "account")
    List<Order>orders;
    @OneToMany(mappedBy = "account")
    List<Favourite>favourites;
    @OneToMany(mappedBy = "account")
    List<CartDetail>cartDetails;
}