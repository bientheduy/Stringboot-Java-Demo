package net.bientheduy.shop.shopmodel;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "nvarchar(60)")
    @NotBlank(message = "Chưa nhập tên loại")
    String name;
        @NotBlank(message = "Chưa nhập slug")
    String slug;
    Integer parentId;
    Boolean active;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
