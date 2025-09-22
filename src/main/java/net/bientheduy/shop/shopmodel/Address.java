package net.bientheduy.shop.shopmodel;

import jakarta.persistence.Column;
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
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    int proviceId;
    int districtId;
    int wardCode;
    @Column(columnDefinition = "nvarchar(250)")
    String address;
    @Column(columnDefinition = "nvarchar(500)")
    String fulladdress;
    Boolean isDefaut;
    Boolean actived;
@ManyToOne @JoinColumn(name = "account_id")
    Account account;
}
