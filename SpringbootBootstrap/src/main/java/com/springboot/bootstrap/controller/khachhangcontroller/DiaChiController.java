package com.springboot.bootstrap.controller.khachhangcontroller;

import com.springboot.bootstrap.entity.DiaChi;
import com.springboot.bootstrap.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/dia_chi")
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("")
    public String getAll (Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<DiaChi> listDC = diaChiService.getAll(pageNo);
        if (keyword != null) {
            listDC = diaChiService.searchDiaChi(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("totalPage", listDC.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDC", listDC);
        return "/pages/index_dia_chi";
    }

    @GetMapping(value = "/editDiaChi/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        DiaChi diaChi = diaChiService.getOne(id);
        model.addAttribute("diaChi", diaChi);
        return "/pages/edit_dia_chi";
    }

    @PostMapping(value = "/deleteDiaChi/{id}")
    public String delete(@PathVariable("id") String id) {
        diaChiService.delete(id);
        return "redirect:/dia_chi";
    }

    @GetMapping(value = "/addDiaChi")
    public String add(Model model) {
        DiaChi diaChi = new DiaChi();
        model.addAttribute("diaChi", diaChi);
        return "/pages/them_dia_chi";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("diaChi") DiaChi diaChi) {
        diaChi.setTrang_thai(1);
        diaChiService.add(diaChi);
        return "redirect:/dia_chi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("diaChi") DiaChi diaChi) {
        diaChiService.update(diaChi);
        return "redirect:/dia_chi";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                         @RequestParam(value = "trangThaiSearch", required = false) String trang_thai,
//
//                         Model model) {
//        int currentPage = page.orElse(0);
//        int pageSize = 5;
//        if (currentPage < 0) {
//            return "redirect:/dia_chi?p=0";
//        }
//        Page<DiaChi> listDC = null;
//
//
//
//        if (trang_thai != null && !trang_thai.isEmpty()) {
//
//            listDC = diaChiService.searchTrangThai(Integer.parseInt(trang_thai), pageNo);
//
//        }  else {
//            listDC = diaChiService.getAll(pageNo);
//        }
//
//
//        model.addAttribute("listDC", listDC);
//        model.addAttribute("totalPage", listDC.getTotalPages());
//        model.addAttribute("currentPage", pageNo);
//
//
//
//
//        return "/pages/index_dia_chi";
//    }
}















