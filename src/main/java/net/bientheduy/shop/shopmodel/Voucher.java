package net.bientheduy.shop.shopmodel;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String code;
    int discountPercent;
    int quantity;
    @Temporal(TemporalType.DATE)
    Date createdAt;
    @Temporal(TemporalType.DATE)
    Date startAt;
    @Temporal(TemporalType.DATE)
    Date endAt;
    Boolean actived;

    @OneToMany(mappedBy = "voucher")
    List<Order> orders;
}
