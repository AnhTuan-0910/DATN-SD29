package com.springboot.bootstrap.controller.nhanviencontroller;

import com.springboot.bootstrap.entity.ChucVu;
import com.springboot.bootstrap.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/viewOne/{id}")
    @ResponseBody
    public ChucVu viewUpdate(@PathVariable("id") String idCV) {
        return chucVuService.getOne(idCV);
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cva") ChucVu chucVu,
                      @RequestParam("ten") String ten,
                      @RequestParam("trangThai") int trangThai,
                      @RequestParam("p") Optional<Integer> p, Model model) {
        chucVu = ChucVu.builder().ma(chucVuService.generateMaCV()).ten(ten).trangThai(Integer.parseInt(String.valueOf(trangThai))).build();
        chucVuService.add(chucVu);
        Page<ChucVu> listCV = chucVuService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listCV", listCV);
        return "redirect:/chuc_vu";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("cvu") ChucVu chucVu,
                         @RequestParam("id_chuc_vu") String idCV,
                         @RequestParam("ma") String ma,
                         @RequestParam("ten") String ten,
                         @RequestParam("trangThai") String trangThai,
                         @RequestParam("p") Optional<Integer> p, Model model) {
        chucVu = ChucVu.builder().ma(ma).ten(ten).trangThai(Integer.parseInt(trangThai)).build();
        chucVuService.update(chucVu, idCV);
        Page<ChucVu> listCV = chucVuService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listCV", listCV);
        return "redirect:/chuc_vu";
    }

    @GetMapping("/search")
    public String search(@RequestParam("p") Optional<Integer> page,
                         @RequestParam(value = "keyword", required = false) String keyword,
                         @RequestParam(value = "trangThaiSearch", required = false) String trangThai,

                         Model model) {
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage < 0) {
            return "redirect:/mau_sac?p=0";
        }
        Page<ChucVu> listCV = null;
        if (keyword != null && !keyword.isEmpty()) {
            listCV = chucVuService.searchCodeOrName(keyword, PageRequest.of(currentPage, pageSize));
        } else if (trangThai != null && !trangThai.isEmpty()) {

            listCV = chucVuService.searchTrangThai(Integer.parseInt(trangThai), PageRequest.of(currentPage, pageSize));

        }  else {
            listCV = chucVuService.getAll(PageRequest.of(currentPage, pageSize));
        }
        model.addAttribute("listCV", listCV);
        return "/pages/chuc_vu";
    }
}
