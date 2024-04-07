package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.utility.ListGioHangShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/thanh_toan")
public class ThanhToanShopController {
    @Autowired
    private ListGioHangShopService listGioHangShopService;
    @GetMapping("")
    public String view(Model model){
        model.addAttribute("listGioHang",listGioHangShopService.getList());
        return "/customer/thanh-toan";
    }
}
