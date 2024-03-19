package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DanhMuc;
import com.springboot.bootstrap.entity.KichThuoc;
import com.springboot.bootstrap.entity.MauSac;
import com.springboot.bootstrap.entity.SanPham;
import com.springboot.bootstrap.entity.ThuongHieu;
import com.springboot.bootstrap.service.DanhMucService;
import com.springboot.bootstrap.service.SanPhamService;
import com.springboot.bootstrap.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/san_pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("")
    public String getAll(@RequestParam("p") Optional<Integer> p, Model model) {
        Page<SanPham> listSP = sanPhamService.getAll(PageRequest.of(p.orElse(0), 5));
        List<DanhMuc> listDM = danhMucService.findAllByTrangThai();
        List<ThuongHieu> listTH = thuongHieuService.findAllByTrangThai();
        model.addAttribute("listTH", listTH);
        model.addAttribute("listDM", listDM);
        model.addAttribute("listSP", listSP);
        return "/pages/san_pham";
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public SanPham viewUpdate(String id) {

        return sanPhamService.detail(id);
    }

    @PostMapping("/updateSP")
    public String updateSP(@ModelAttribute("spu") SanPham sanPham,
                           @RequestParam("id") String id,
                           @RequestParam("ma") String ma,
                           @RequestParam("danhMuc") String idDM,
                           @RequestParam("thuongHieu") String idTH,
                           @RequestParam("ten") String tenSP,
                           @RequestParam(value = "trangThaiSt", required = false) String trangThaiSt, Model model) {
        int trangThai = (trangThaiSt != null && trangThaiSt.equals("on")) ? 1 : 0;
        sanPham = SanPham.builder()
                .ma(ma)
                .ten(tenSP)
                .danhMuc(DanhMuc.builder().id(idDM).build())
                .thuongHieu(ThuongHieu.builder().id(idTH).build())
                .trangThai(trangThai).build();
        sanPhamService.update(id, sanPham);
        return "redirect:/san_pham";
    }

}
