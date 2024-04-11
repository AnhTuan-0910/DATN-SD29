package com.springboot.bootstrap.controller.nhanviencontroller;

import com.springboot.bootstrap.entity.ChucVu;
import com.springboot.bootstrap.entity.NhanVien;
import com.springboot.bootstrap.service.ChucVuService;
import com.springboot.bootstrap.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;
import java.util.List;
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
        java.util.List<ChucVu> listCV = chucVuService.findAllByTrangThai();
        model.addAttribute("listCV", listCV);
        return "/pages/nhan_vien";
    }

    @GetMapping("/viewOne/{id}")
    @ResponseBody
    public NhanVien viewUpdate(@PathVariable("id") String idNV) {

        return nhanVienService.getOne(idNV);
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("nva") NhanVien nhanVien,
                      @RequestParam("ten") String ten,
                      @RequestParam("trangThai") String trangThai,
                      @RequestParam("email") String email,
                      @RequestParam("diaChi") String diaChi,
                      @RequestParam("sdt") String sdt,
//                      @RequestParam("gioiTinh") String gioiTinh,
//                      @RequestParam("matKhau") String matKhau,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        nhanVien = NhanVien.builder().ma(nhanVienService.generateMaNV()).ten(ten).trangThai(Integer.parseInt(trangThai)).email(email).diaChi(diaChi).sdt(sdt).build();
        nhanVienService.add(nhanVien);
        Page<NhanVien> listNV = nhanVienService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        java.util.List<ChucVu> listCV = chucVuService.findAllByTrangThai();
        model.addAttribute("listCV", listCV);
        return "redirect:/nhan_vien";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nvu") NhanVien nhanVien,
                         @RequestParam("id_nhan_vien") String idNV,
                         @RequestParam("ma") String ma,
                         @RequestParam("ten") String ten,
                         @RequestParam("trangThai") String trangThai,
                         @RequestParam("email") String email,
                         @RequestParam("diaChi") String diaChi,
                         @RequestParam("sdt") String sdt,
                         @RequestParam("p") Optional<Integer> p, Model model) {
        nhanVien = NhanVien.builder().ma(ma).ten(ten).trangThai(Integer.parseInt(trangThai)).email(email).diaChi(diaChi).sdt(sdt).build();
        nhanVienService.update(nhanVien, idNV);
        Page<NhanVien> listNV = nhanVienService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listNV", listNV);
        List<ChucVu> listCV = chucVuService.findAllByTrangThai();
        model.addAttribute("listCV", listCV);
        return "redirect:/nhan_vien";
    }

    @GetMapping("/search")
    public String search(@RequestParam("p") Optional<Integer> page,
                         @RequestParam(value = "keyword", required = false) String keyword,
                         @RequestParam(value = "trangThaiSearch", required = false) String trangThai,

                         Model model) {
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage < 0) {
            return "redirect:/nhan_vien?p=0";
        }
        Page<NhanVien> listNV = null;
        if (keyword != null && !keyword.isEmpty()) {
            listNV = nhanVienService.searchCodeOrName(keyword, PageRequest.of(currentPage, pageSize));
        } else if (trangThai != null && !trangThai.isEmpty()) {

            listNV = nhanVienService.searchTrangThai(Integer.parseInt(trangThai), PageRequest.of(currentPage, pageSize));

        } else {
            listNV = nhanVienService.getAll(PageRequest.of(currentPage, pageSize));
        }
        model.addAttribute("listNV", listNV);
        return "/pages/nhan_vien";
    }
}
