package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.entity.GioHang;
import com.springboot.bootstrap.entity.GioHangChiTiet;
import com.springboot.bootstrap.repository.GioHangChiTietRepository;
import com.springboot.bootstrap.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/shop/gio-hang")
public class GioHangController{

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @GetMapping
    public String getAll(Model model){
        List<GioHangChiTiet> listGioHang=gioHangChiTietRepository.findAllByGioHang_KhachHang_Ma("KH001");
        model.addAttribute("listGioHangCT",listGioHang);
        GioHang gioHang=gioHangRepository.findAllByKhachHang_Ma("KH001");
        model.addAttribute("gioHang",gioHang);
        return "/customer/gio-hang";
    }

    @PostMapping("/update-so-luong")
    public String updateSoLuong(@RequestParam(name = "idGhct") UUID idGhct,
                              @RequestParam(name = "soLuong") int soLuong) {
        GioHangChiTiet gioHangChiTiet=gioHangChiTietRepository.findById(idGhct).orElse(null);
        gioHangChiTiet.setSoLuong(soLuong);
        gioHangChiTietRepository.save(gioHangChiTiet);
        return "redirect:/shop/gio-hang";
    }
}

