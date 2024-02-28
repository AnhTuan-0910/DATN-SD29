package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DiaChi;
import com.springboot.bootstrap.service.DiaChiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/dia_chi")
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("")
    public String getAll (@RequestParam("p") Optional<Integer> p, Model model) {
        Page<DiaChi> listDC = diaChiService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listDC", listDC);
        return "/pages/dia_chi";
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public DiaChi viewUpdate(UUID idDiaChi) {
        return diaChiService.getOne(idDiaChi);
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("dca") DiaChi dc,
                      @RequestParam("diaChi") String diaChi,
                      @RequestParam("huyen") String huyen,
                      @RequestParam("tinh") String tinh,
                      @RequestParam("thanhPho") String thanhPho,
                      @RequestParam("quocGia") String quocGia,
                      @RequestParam("sdt") String sdt,
                      @RequestParam("trang_thai") String trang_thai,
                      @RequestParam("p") Optional<Integer> p,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", result.getFieldErrors());
            return "";
        }
        dc = DiaChi.builder().diaChi(diaChi).huyen(huyen).tinh(tinh).thanhPho(thanhPho).quocGia(quocGia).sdt(sdt).trang_thai(Integer.parseInt(trang_thai)).build();
        diaChiService.add(dc);
        Page<DiaChi> listDC = diaChiService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listDC", listDC);
        return "redirect:/dia_chi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dcu") DiaChi dc,
                         @RequestParam("idDiaChi") UUID idDiaChi,
                         @RequestParam("diaChi") String diaChi,
                         @RequestParam("huyen") String huyen,
                         @RequestParam("tinh") String tinh,
                         @RequestParam("thanhPho") String thanhPho,
                         @RequestParam("quocGia") String quocGia,
                         @RequestParam("sdt") String sdt,
                         @RequestParam("trang_thai") String trang_thai,
                         @RequestParam("p") Optional<Integer> p,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", result.getFieldErrors());
            return "";
        }
        dc = DiaChi.builder().diaChi(diaChi).huyen(huyen).tinh(tinh).thanhPho(thanhPho).quocGia(quocGia).sdt(sdt).trang_thai(Integer.parseInt(trang_thai)).build();
        diaChiService.update(dc, idDiaChi);
        Page<DiaChi> listDC = diaChiService.getAll(PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listDC", listDC);
        return "redirect:/dia_chi";
    }

    @GetMapping("/search")
    public String search(@RequestParam("p") Optional<Integer> page,
                         @RequestParam(value = "trangThaiSearch", required = false) String trang_thai,

                         Model model) {
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage < 0) {
            return "redirect:/dia_chi?p=0";
        }
        Page<DiaChi> listDC = null;



        if (trang_thai != null && !trang_thai.isEmpty()) {

            listDC = diaChiService.searchTrangThai(Integer.parseInt(trang_thai), PageRequest.of(currentPage, pageSize));

        }  else {
            listDC = diaChiService.getAll(PageRequest.of(currentPage, pageSize));
        }


        model.addAttribute("listDC", listDC);




        return "/pages/dia_chi";
    }
}















