package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.ChucVu;
import com.springboot.bootstrap.service.ChucVuService;
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

import java.util.Date;
import java.util.Optional;

@RequestMapping("/chuc_vu")
@Controller
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("")
    public String getAll(@RequestParam("p") Optional<Integer> p, Model model) {
        Page<ChucVu> listCV = chucVuService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listCV", listCV);
        return "/pages/chuc_vu";
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public ChucVu viewUpdate(String idCV) {
        return chucVuService.getOne(idCV);
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cv") ChucVu chucVu,
                      @RequestParam("ma") String ma,
                      @RequestParam("ten") String ten,
                      @RequestParam("trangThai") int trangThai,
                      @RequestParam("taoLuc") Date taoLuc,
                      @RequestParam("suaLuc") Date suaLuc,
                      @RequestParam("taoBoi") String taoBoi,
                      @RequestParam("suaBoi") String suaBoi,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        chucVu = ChucVu.builder().ma(ma).ten(ten).trangThai(Integer.parseInt(String.valueOf(trangThai))).taoLuc(taoLuc).suaLuc(suaLuc).taoBoi(taoBoi).suaBoi(suaBoi).build();
        chucVuService.add(chucVu);
        Page<ChucVu> listCV = chucVuService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listCV", listCV);
        return "redirect:/chuc_vu";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("cv") ChucVu chucVu,
                         @RequestParam("id") String id,
                         @RequestParam("ma") String ma,
                         @RequestParam("ten") String ten,
                         @RequestParam("trangThai") int trangThai,
                         @RequestParam("taoLuc") Date taoLuc,
                         @RequestParam("suaLuc") Date suaLuc,
                         @RequestParam("taoBoi") String taoBoi,
                         @RequestParam("suaBoi") String suaBoi,
                         @RequestParam("p") Optional<Integer> p, Model model) {
        chucVu = ChucVu.builder().ma(ma).ten(ten).trangThai(Integer.parseInt(String.valueOf(trangThai))).taoLuc(taoLuc).suaLuc(suaLuc).taoBoi(taoBoi).suaBoi(suaBoi).build();
        chucVuService.update(chucVu, id);
        Page<ChucVu> listCV = chucVuService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listCV", listCV);
        return "redirect:/chuc_vu";
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
//            return "redirect:/mau_sac?p=0";
//        }
//        Page<DanhMuc> listCV = null;
//        if (keyword != null && !keyword.isEmpty()) {
//            listCV = chucVuService.searchCodeOrName(keyword, PageRequest.of(currentPage, pageSize));
//        } else if (trangThai != null && !trangThai.isEmpty()) {
//
//            listCV = chucVuService.searchTrangThai(Integer.parseInt(trangThai), PageRequest.of(currentPage, pageSize));
//
//        }  else {
//            listCV = chucVuService.getAll(PageRequest.of(currentPage, pageSize));
//        }
//        model.addAttribute("listCV", listCV);
//        return "/pages/chuc_vu";
//    }
}
