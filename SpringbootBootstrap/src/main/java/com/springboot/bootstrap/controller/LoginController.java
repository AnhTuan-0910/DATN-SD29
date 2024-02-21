package com.springboot.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginPage(){
        return "auth-login";
    }

    @RequestMapping("/home")
    public String loginSubmit(){
        return "/pages/landing_page";
    }
    @RequestMapping("/giao_dich")
    public String GiaoDich(){
        return "/pages/giao_dich";
    }
    @RequestMapping("/hoa_don")
    public String HoaDon(){
        return "/pages/hoa_don";
    }
    @RequestMapping("/sanpham")
    public String SanPham(){
        return "/pages/san_pham";
    }

    @RequestMapping("/khach_hang")
    public String KhachHang(){
        return "/pages/khach_hang";
    }
    @RequestMapping("/nhan_vien")
    public String NhanVien(){
        return "/pages/nhan_vien";
    }
    @RequestMapping("/voucher")
    public String Voucher(){
        return "/pages/voucher";
    }
}
