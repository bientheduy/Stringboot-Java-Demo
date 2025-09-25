package net.bientheduy.shop.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bientheduy.shop.CategoryRepository;
import net.bientheduy.shop.shopmodel.Category;
import net.bientheduy.shop.ProductRepository;
import net.bientheduy.shop.shopmodel.Product;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @RequestMapping("/")
    public String home(Model model){
        List<Category> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        try {
            categories = categoryRepository.findByActive(true, Sort.by(Direction.DESC, "id"));
            products = productRepository.findByActive(true);
        } catch (Exception ex) {
            // Ignore to allow home page to render without DB
        }
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("page", 0);
        model.addAttribute("totalPage", 0);
        model.addAttribute("active", "home");
        return "home";
    }
}
