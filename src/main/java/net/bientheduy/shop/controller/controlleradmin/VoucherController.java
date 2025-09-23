package net.bientheduy.shop.controller.controlleradmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;
import net.bientheduy.shop.VoucherRepository;
import net.bientheduy.shop.shopmodel.Voucher;

@Controller
public class VoucherController {

    @Autowired
    VoucherRepository voucherRepository;

    // danh sách
    @GetMapping("/admin/vouchers")
    public String index(Model model) {
        List<Voucher> vouchers = voucherRepository.findAll(Sort.by(Direction.DESC, "id"));
        model.addAttribute("vouchers", vouchers);
        model.addAttribute("active", "voucher");
        return "admin/vouchers/list";
    }

    // form thêm mới
    @GetMapping("/admin/vouchers/add")
    public String add(Model model) {
        model.addAttribute("voucher", new Voucher());
        model.addAttribute("active", "voucher");
        return "admin/vouchers/add";
    }

    // insert
    @PostMapping("/admin/vouchers/add")
    public String insert(@Valid @ModelAttribute("voucher") Voucher voucher,
                         Errors errors,
                         Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("active", "voucher");
            model.addAttribute("message", "Vui lòng kiểm tra thông tin nhập");
            return "admin/vouchers/add";
        }
        voucherRepository.save(voucher);
        return "redirect:/admin/vouchers";
    }

    // form sửa
    @GetMapping("/admin/vouchers/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Voucher voucher = voucherRepository.findById(id).orElse(null);
        if (voucher == null) {
            return "redirect:/admin/vouchers";
        }
        model.addAttribute("voucher", voucher);
        model.addAttribute("active", "voucher");
        return "admin/vouchers/edit";
    }

    // update
    @PostMapping("/admin/vouchers/update")
    public String update(@Valid @ModelAttribute("voucher") Voucher voucher,
                         Errors errors,
                         Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("active", "voucher");
            model.addAttribute("message", "Vui lòng kiểm tra thông tin nhập");
            return "admin/vouchers/edit";
        }
        voucherRepository.save(voucher);
        return "redirect:/admin/vouchers";
    }

    // delete
    @GetMapping("/admin/vouchers/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Voucher voucher = voucherRepository.findById(id).orElse(null);
        if (voucher != null) {
            voucherRepository.delete(voucher);
        }
        return "redirect:/admin/vouchers";
    }
}
