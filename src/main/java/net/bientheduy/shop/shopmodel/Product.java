package net.bientheduy.shop.shopmodel;

import java.math.BigDecimal;
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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "nvarchar(60)")
    @NotBlank(message = "Chưa nhập tên sản phẩm")
    String name;
    String slug;
    @Column(columnDefinition = "nvarchar(500)")
    String description;
    @Column(columnDefinition = "nvarchar(255)")
    String image;
    @NotNull(message = "Chưa nhập giá sản phẩm")
    @Positive(message = "Giá phải lớn hơn 0")
    Integer price;
    @NotNull(message = "Chưa nhập giá sản phẩm")
    @Positive(message = "Giá phải lớn hơn 0")
    int quantity;
     
    Boolean active;
    @ManyToOne @JoinColumn(name = "category_id")
    Category category;
    @OneToMany(mappedBy = "product")
    List<Favourite>favourites;
    @OneToMany(mappedBy = "product")
    List<CartDetail>cartDetails;
}
