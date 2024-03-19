package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DiaChi;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.service.DiaChiService;
import com.springboot.bootstrap.service.KhachHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("/khach_hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;


    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("")
    public String getAll(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<KhachHang> listKH = khachHangService.getAll(pageNo);
        if (keyword != null) {
            listKH = khachHangService.searchCodeOrName(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", listKH.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listKH", listKH);
        return "/pages/index_khach_hang";
    }

    @GetMapping(value = "/editKhachHang/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        KhachHang khachHang = khachHangService.getOne(id);
        model.addAttribute("khachHang", khachHang);
        List<DiaChi> listDC = diaChiService.findAll();
        model.addAttribute("listDC", listDC);
        return "/pages/edit_khach_hang";
    }

    @PostMapping(value = "/deleteKhachHang/{id}")
    public String delete(@PathVariable("id") String id){
        khachHangService.delete(id);
        return "redirect:/khach_hang";
    }

    @RequestMapping(value = "/addKhachHang")
    public String add(Model model) {
        KhachHang khachHang = new KhachHang();
        List<DiaChi> listDC = diaChiService.findAll();
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("listDC",listDC);
        return "/pages/them_khach_hang";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("khachHang") KhachHang khachHang) {
        khachHang.setTrangThai(1);
        // xu ly upload file
//        storageService.store(fileImage);
//        String fileName = fileImage.getOriginalFilename();
//        khachHang.setAnhNhanVien(fileName);
        khachHangService.add(khachHang);
        return "redirect:/khach_hang";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("khachHang") KhachHang khachHang) {
        khachHangService.update(khachHang);
        return "redirect:/khach_hang";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam("p") Optional<Integer> page,
//                         @RequestParam(value = "keyword", required = false) String keyword,
//                         @RequestParam(value = "trangThaiSearch", required = false) String trangThai,
//
//                         Model model) {
//        int currentPage = page.orElse(0);
//        int pageSize = 5;
//        if (currentPage < 0) {
//            return "redirect:/khach_hang?p=0";
//        }
//        Page<KhachHang> listKH = null;
//
//
//
//        if (keyword != null && !keyword.isEmpty()) {
//            listKH = khachHangService.searchCodeOrName(keyword, PageRequest.of(currentPage, pageSize));
//        } else if (trangThai != null && !trangThai.isEmpty()) {
//
//            listKH = khachHangService.searchTrangThai(Integer.parseInt(trangThai), PageRequest.of(currentPage, pageSize));
//
//        }  else {
//            listKH = khachHangService.getAll(PageRequest.of(currentPage, pageSize));
//        }
//
//
//        model.addAttribute("listKH", listKH);
//
//
//
//
//        return "/pages/index_khach_hang";
//    }
}
