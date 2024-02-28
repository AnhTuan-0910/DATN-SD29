package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.service.ChucVuService;
import com.springboot.bootstrap.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.Date;
import java.util.Optional;

@RequestMapping("/nhan_vien")
@Controller
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("")
    public String getAll(@RequestParam("p") Optional<Integer> p, Model model) {
        Page<NhanVien> listNV = nhanVienService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        return "/pages/nhan_vien";
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public NhanVien viewUpdate(String idNV) {
        return nhanVienService.getOne(idNV);
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("nv") NhanVien nhanVien,
                      @RequestParam("ma") String ma,
                      @RequestParam("email") String email,
                      @RequestParam("ten") String ten,
                      @RequestParam("gioiTinh") String gioiTinh,
                      @RequestParam("ngaySinh") Date ngaySinh,
                      @RequestParam("anhNhanVien") Image anhNhanVien,
                      @RequestParam("diaChi") String diaChi,
                      @RequestParam("sdt") String sdt,
                      @RequestParam("matKhau") String matKhau,
                      @RequestParam("trangThai") int trangThai,
                      @RequestParam("taoLuc") Date taoLuc,
                      @RequestParam("suaLuc") Date suaLuc,
                      @RequestParam("taoBoi") String taoBoi,
                      @RequestParam("suaBoi") String suaBoi,
                      @RequestParam("p") Optional<Integer> p, Model model) {
//        nhanVien = NhanVien.builder().ma(ma).ten(ten).trangThai(Integer.parseInt(trangThai)).build();
        nhanVienService.add(nhanVien);
        Page<NhanVien> listNV = nhanVienService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        return "redirect:/nhan_vien";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("nv") NhanVien nhanVien,
                         @RequestParam("ma") String ma,
                         @RequestParam("email") String email,
                         @RequestParam("ten") String ten,
                         @RequestParam("gioiTinh") String gioiTinh,
                         @RequestParam("ngaySinh") Date ngaySinh,
                         @RequestParam("anhNhanVien") Image anhNhanVien,
                         @RequestParam("diaChi") String diaChi,
                         @RequestParam("sdt") String sdt,
                         @RequestParam("matKhau") String matKhau,
                         @RequestParam("trangThai") int trangThai,
                         @RequestParam("taoLuc") Date taoLuc,
                         @RequestParam("suaLuc") Date suaLuc,
                         @RequestParam("taoBoi") String taoBoi,
                         @RequestParam("suaBoi") String suaBoi,
                         @RequestParam("p") Optional<Integer> p, Model model) {
//        nhanVien = NhanVien.builder().ma(ma).ten(ten).trangThai(Integer.parseInt(trangThai)).build();
        nhanVienService.update(nhanVien, ma);
        Page<NhanVien> listNV = nhanVienService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        return "redirect:/nhan_vien";
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
//            return "redirect:/nhan_vien?p=0";
//        }
//        Page<NhanVien> listNV = null;
//        if (keyword != null && !keyword.isEmpty()) {
//            listNV = nhanVienService.searchCodeOrName(keyword, PageRequest.of(currentPage, pageSize));
//        } else if (trangThai != null && !trangThai.isEmpty()) {
//
//            listNV = nhanVienService.searchTrangThai(Integer.parseInt(trangThai), PageRequest.of(currentPage, pageSize));
//
//        }  else {
//            listNV = nhanVienService.getAll(PageRequest.of(currentPage, pageSize));
//        }
//        model.addAttribute("listNV", listNV);
//        return "/pages/nhan_vien";
//    }
}
