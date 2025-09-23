package net.bientheduy.shop.shopmodel;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotBlank(message = "Chưa nhập mã giảm giá")
    String code;
    @NotNull(message = "Chưa nhập % giảm giá")
    @Range(min = 1, max = 70, message = "Giá trị giảm giá chỉ được từ 1- 70%")
    Integer discountPercent;
    @NotNull(message = "Chưa nhập % số lượng")
    @Positive(message = "Số lượng > 0")
    Integer quantity;
    @Temporal(TemporalType.DATE)
    Date createdAt;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Chưa nhập ngày bắt đầu")
    Date startAt;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Chưa nhập ngày kết thúc")
    Date endAt;
    Boolean actived;

    @OneToMany(mappedBy = "voucher")
    List<Order> orders;
}
