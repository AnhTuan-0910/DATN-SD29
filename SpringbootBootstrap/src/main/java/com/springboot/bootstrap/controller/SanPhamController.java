package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.SanPham;
import com.springboot.bootstrap.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/san_pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @GetMapping("")
    public String getAll(@RequestParam("p") Optional<Integer> p, Model model){
        Page<SanPham> listSP = sanPhamService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listSP",listSP);
        return "/pages/san_pham";
    }
}
