package com.springboot.bootstrap.controller;


import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.service.AnhService;
import com.springboot.bootstrap.service.DanhMucService;
import com.springboot.bootstrap.service.KichThuocService;
import com.springboot.bootstrap.service.MauSacService;
import com.springboot.bootstrap.service.SanPhamCTService;
import com.springboot.bootstrap.service.SanPhamService;
import com.springboot.bootstrap.service.ThuongHieuService;
import com.springboot.bootstrap.utility.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/spct")
public class SPCTController {
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @Autowired
    private QRCodeGenerator qrCodeGenerator;
    @Autowired
    private AnhService anhService;

    @GetMapping("/viewDetail/{id}")
    public String getAll(@PathVariable("id") String idSP, @RequestParam("p") Optional<Integer> p, Model model) {
        Page<SanPhamCT> listSPCT = sanPhamCTService.getAllBySP(idSP, PageRequest.of(p.orElse(0), 5));
        model.addAttribute("listSPCT", listSPCT);
        model.addAttribute("qrCodeGenerator", qrCodeGenerator);
        return "/pages/spct";
    }

}
