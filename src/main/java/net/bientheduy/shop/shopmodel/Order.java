package net.bientheduy.shop.shopmodel;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;
    int total;
    int feeShip;
    int discount;
    int paymentMethod;
    int paymentStatus;
    int status;
    @Column(columnDefinition = "nvarchar(500)")
    String shipAddress;
    @ManyToOne @JoinColumn(name = "account_id")
    Account account;
    @ManyToOne @JoinColumn(name = "voucher_id")
    Voucher voucher;
    

}
