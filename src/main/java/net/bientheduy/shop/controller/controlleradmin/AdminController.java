package net.bientheduy.shop.controller.controlleradmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping("/admin")
    public String home(Model model){
        model.addAttribute("active", "index");
        return "admin/index";
    }
}
