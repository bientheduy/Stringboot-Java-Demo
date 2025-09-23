package net.bientheduy.shop.controller.controlleradmin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import net.bientheduy.shop.CategoryRepository;
import net.bientheduy.shop.ProductRepository;
import net.bientheduy.shop.shopmodel.Category;
import net.bientheduy.shop.shopmodel.Product;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ServletContext servletContext;

    // Danh sách sản phẩm
    @RequestMapping("/admin/products")
    public String index(Model model) {
        List<Product> products = productRepository.findByActive(true);
        model.addAttribute("products", products);
        model.addAttribute("active", "products");
        return "admin/products/list";
    }

    // Gọi form thêm mới
    @GetMapping("/admin/products/add")
    public String add(Model model) {
        model.addAttribute("active", "product");
        model.addAttribute("product", new Product());
        List<Category> categories = categoryRepository.findByActive(true, Sort.by(Direction.DESC, "id"));
        model.addAttribute("categories", categories);
        return "admin/products/add";
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/admin/products/add")
    public String insert(Model model,
                         @Valid @ModelAttribute Product product,
                         Errors errors,
                         @RequestParam("imageProduct") MultipartFile image) {
        model.addAttribute("active", "product");
        List<Category> categories = categoryRepository.findByActive(true, Sort.by(Direction.DESC, "id"));
        model.addAttribute("categories", categories);

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin");
            return "admin/products/add";
        }

        try {
            if (!image.isEmpty()) {
                String fileName = image.getOriginalFilename();
                String filePath = servletContext.getRealPath("/images/" + fileName);

                Path parentDir = Path.of(filePath).getParent();
                if (!Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                File file = new File(filePath);
                image.transferTo(file);
                product.setImage(fileName);
            }
            Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
            product.setCategory(category);
            product.setActive(true);
            productRepository.save(product);

            return "redirect:/admin/products";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi lưu sản phẩm");
        }

        return "admin/products/add";
    }

    // Gọi form chỉnh sửa
    @GetMapping("/admin/products/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("active", "product");
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        List<Category> categories = categoryRepository.findByActive(true, Sort.by(Direction.DESC, "id"));
        model.addAttribute("categories", categories);
        return "admin/products/edit";
    }

    // Xử lý update
    @PostMapping("/admin/products/update")
    public String update(Model model,
                         @Valid @ModelAttribute Product product,
                         Errors errors,
                         @RequestParam("imageProduct") MultipartFile image) {
        model.addAttribute("active", "product");
        List<Category> categories = categoryRepository.findByActive(true, Sort.by(Direction.DESC, "id"));
        model.addAttribute("categories", categories);

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin");
            return "admin/products/edit";
        }

        try {
            if (!image.isEmpty()) {
                String fileName = image.getOriginalFilename();
                String filePath = servletContext.getRealPath("/images/" + fileName);

                Path parentDir = Path.of(filePath).getParent();
                if (!Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                File file = new File(filePath);
                image.transferTo(file);
                product.setImage(fileName);
            } else {
                // Nếu không upload ảnh mới thì giữ ảnh cũ
                Product oldProduct = productRepository.findById(product.getId()).orElse(null);
                if (oldProduct != null) {
                    product.setImage(oldProduct.getImage());
                }
            }

            Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
            product.setCategory(category);
            product.setActive(true);
            productRepository.save(product);

            return "redirect:/admin/products";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi cập nhật sản phẩm");
        }

        return "admin/products/edit";
    }

    // Xóa sản phẩm
    @GetMapping("/admin/products/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
        return "redirect:/admin/products";
    }
}
