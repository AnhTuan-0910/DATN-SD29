package com.springboot.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class CustomerShopController {

    @RequestMapping("")
    public String index() {
        return "/customer/index";
    }

    @RequestMapping("/tat-ca-san-pham")
    public String tatCaSanPham() {
        return "/customer/tat-ca-san-pham";
    }

    @RequestMapping("/danh-muc-giay")
    public String danhMucSanPham() {
        return "/customer/danh-muc-san-pham";
    }

    @RequestMapping("/detailSP")
    public String detailSP() {
        return "/customer/detailSP";
    }

    @RequestMapping("/thanh-toan")
    public String thanhToan() {
        return "/customer/thanh-toan";
    }

    @RequestMapping("/gio-hang")
    public String gioHang() {
        return "/customer/gio-hang";
    }

    @RequestMapping("/voucher")
    public String voucher() {
        return "/customer/voucher";
    }

    @RequestMapping("/about")
    public String about() {
        return "/customer/about";
    }
}
