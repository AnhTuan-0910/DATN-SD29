package com.springboot.bootstrap.controller.nhanviencontroller;

import com.springboot.bootstrap.entity.ChucVu;
import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.service.ChucVuService;
import com.springboot.bootstrap.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/nhan_vien")
@Controller
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("")
    public String getAll(@RequestParam("p") Optional<Integer> p, Model model) {
        Page<NhanVien> listNV = nhanVienService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        return "/pages/nhan_vien";
    }

    @GetMapping("/viewOne/{id}")
    @ResponseBody
    public NhanVien viewUpdate(@PathVariable("id") String idNV) {
        return nhanVienService.getOne(idNV);
    }

    @PostMapping("/add")
    public String add(@RequestParam("ten") String ten,
                      @RequestParam("email") String email,
                      @RequestParam("matKhau") String matKhau,
                      @RequestParam("ngaySinh") Date ngaySinh,
                      @RequestParam("gioiTinh") Integer gioiTinh,
                      @RequestParam("diaChi") String diaChi,
                      @RequestParam("sdt") String sdt) {
        NhanVien nhanVien = NhanVien.builder().ten(ten).trangThai(1).gioiTinh(gioiTinh).ngaySinh(ngaySinh).matKhau(passwordEncoder.encode(matKhau)).email(email).diaChi(diaChi).sdt(sdt).build();
        nhanVienService.add(nhanVien);
        return "redirect:/nhan_vien";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id_nhan_vien") String idNV,
                         @RequestParam("ten") String ten,
                         @RequestParam("email") String email,
                         @RequestParam("diaChi") String diaChi,
                         @RequestParam("sdt") String sdt) {
        NhanVien nhanVien = nhanVienService.getOne(idNV);
        nhanVien.setTen(ten);
        nhanVien.setEmail(email);
        nhanVien.setDiaChi(diaChi);
        nhanVien.setSdt(sdt);
        nhanVienService.update(nhanVien, idNV);
        return "redirect:/nhan_vien";
    }

    @GetMapping("/search")
    public String search(@RequestParam("p") Optional<Integer> page,
                         @RequestParam(value = "keyword", required = false) String keyword,
                         Model model) {
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage < 0) {
            return "redirect:/nhan_vien?p=0";
        }
        Page<NhanVien> listNV = nhanVienService.searchByEmail(keyword,PageRequest.of(page.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        return "/pages/nhan_vien";
    }
}
