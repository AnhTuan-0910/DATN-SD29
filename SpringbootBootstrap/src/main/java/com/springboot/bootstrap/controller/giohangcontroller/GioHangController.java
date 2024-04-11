package com.springboot.bootstrap.controller.giohangcontroller;


import com.springboot.bootstrap.entity.FormatHelper;
import com.springboot.bootstrap.entity.GioHang;
import com.springboot.bootstrap.entity.GioHangChiTiet;
import com.springboot.bootstrap.repository.GioHangChiTietRepository;
import com.springboot.bootstrap.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/gio-hang")
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
        model.addAttribute("formatHelper", new FormatHelper());
        return "/customer/gio-hang";
    }

    @PostMapping("/update-so-luong/{idGhct}")
//    @ResponseBody
    public String updateSoLuong(@PathVariable(value = "idGhct", required = false) UUID idGhct
                                ,@RequestParam(name = "soLuong") int soLuong) {
            GioHangChiTiet gioHangChiTiet=gioHangChiTietRepository.findById(idGhct).orElse(null);
            gioHangChiTiet.setSoLuong(soLuong);
            gioHangChiTietRepository.save(gioHangChiTiet);
        return "redirect:/shop/gio-hang";
    }

    @GetMapping("/delete/{idGhct}")
    public String deleteItem(Model model,
                             @PathVariable("idGhct") UUID idGhct) {
        gioHangChiTietRepository.deleteById(idGhct);
        return "redirect:/shop/gio-hang";
    }


}

