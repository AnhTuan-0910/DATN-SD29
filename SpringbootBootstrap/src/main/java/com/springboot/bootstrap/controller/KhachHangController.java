package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DiaChi;
import com.springboot.bootstrap.entity.KhachHang;
import com.springboot.bootstrap.service.DiaChiService;
import com.springboot.bootstrap.service.KhachHangService;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/khach_hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("")
    public String getAll(@RequestParam("p") Optional<Integer> p, Model model) {
        Page<KhachHang> listKH = khachHangService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listKH", listKH);
        List<DiaChi> listDC = diaChiService.findAll();
        model.addAttribute("listDC", listDC);
        return "/pages/khach_hang";
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public KhachHang viewUpdate(UUID idKhachHang) {
        return khachHangService.getOne(idKhachHang);
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("kha") KhachHang khachHang,
                      @RequestParam("ma") String ma,
                      @RequestParam("ten") String ten,
                      @RequestParam("gioiTinh") String gioiTinh,
                      @RequestParam("email") String email,
                      @RequestParam("matKhau") String matKhau,
                      @RequestParam("anhNhanVien") String anhNhanVien,
                      @RequestParam("sdt") String sdt,
                      @RequestParam("trangThai") String trangThai,
                      @RequestParam("p") Optional<Integer> p,
                      Model model) {

        khachHang = KhachHang.builder().ma(ma).ten(ten).gioiTinh(gioiTinh).email(email).matKhau(matKhau).anhNhanVien(anhNhanVien).sdt(sdt).trangThai(Integer.parseInt(trangThai)).build();
        khachHangService.add(khachHang);
        Page<KhachHang> listKH = khachHangService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listKH", listKH);
        List<DiaChi> listDC = diaChiService.findAll();
        model.addAttribute("listDC", listDC);
        return "redirect:/khach_hang";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("khu") KhachHang khachHang,
                         @RequestParam("idKhachHang") UUID idKhachHang,
                         @RequestParam("ma") String ma,
                         @RequestParam("ten") String ten,
                         @RequestParam("gioiTinh") String gioiTinh,
                         @RequestParam("email") String email,
                         @RequestParam("matKhau") String matKhau,
                         @RequestParam("anhNhanVien") String anhNhanVien,
                         @RequestParam("sdt") String sdt,
                         @RequestParam("trangThai") String trangThai,
                         @RequestParam("p") Optional<Integer> p,
                         Model model) {

        khachHang = KhachHang.builder().ma(ma).ten(ten).gioiTinh(gioiTinh).email(email).matKhau(matKhau).anhNhanVien(anhNhanVien).sdt(sdt).trangThai(Integer.parseInt(trangThai)).build();
        khachHangService.update(khachHang, idKhachHang);
        Page<KhachHang> listKH = khachHangService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listKH", listKH);
        List<DiaChi> listDC = diaChiService.findAll();
        model.addAttribute("listDC", listDC);
        return "redirect:/khach_hang";
    }

    @GetMapping("/search")
    public String search(@RequestParam("p") Optional<Integer> page,
                         @RequestParam(value = "keyword", required = false) String keyword,
                         @RequestParam(value = "trangThaiSearch", required = false) String trangThai,

                         Model model) {
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage < 0) {
            return "redirect:/khach_hang?p=0";
        }
        Page<KhachHang> listKH = null;


        if (keyword != null && !keyword.isEmpty()) {
            listKH = khachHangService.searchCodeOrName(keyword, PageRequest.of(currentPage, pageSize));
        } else if (trangThai != null && !trangThai.isEmpty()) {

            listKH = khachHangService.searchTrangThai(Integer.parseInt(trangThai), PageRequest.of(currentPage, pageSize));

        } else {
            listKH = khachHangService.getAll(PageRequest.of(currentPage, pageSize));
        }


        model.addAttribute("listKH", listKH);


        return "/pages/khach_hang";
    }
}
