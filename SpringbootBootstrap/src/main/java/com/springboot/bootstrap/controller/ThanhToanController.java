package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.DanhMuc;
import com.springboot.bootstrap.entity.KichThuoc;
import com.springboot.bootstrap.entity.MauSac;
import com.springboot.bootstrap.entity.SanPhamCT;
import com.springboot.bootstrap.entity.ThuongHieu;
import com.springboot.bootstrap.service.DanhMucService;
import com.springboot.bootstrap.service.KichThuocService;
import com.springboot.bootstrap.service.MauSacService;
import com.springboot.bootstrap.service.SanPhamCTService;
import com.springboot.bootstrap.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/giao_dich")
public class ThanhToanController {
    @Autowired
    private SanPhamCTService sanPhamCTService;
    @Autowired
    private KichThuocService kichThuocService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("")
    public String getAll(Model model) {

        List<DanhMuc> listDM = danhMucService.findAllByTrangThai();
        List<ThuongHieu> listTH = thuongHieuService.findAllByTrangThai();
        List<KichThuoc> listKT = kichThuocService.findAllByTrangThai();
        List<MauSac> listMS = mauSacService.findAllByTrangThai();
        model.addAttribute("listTH", listTH);
        model.addAttribute("listDM", listDM);
        model.addAttribute("listKT", listKT);
        model.addAttribute("listMS", listMS);
        return "/pages/giao_dich";
    }

    @GetMapping("/spct")
    @ResponseBody
    public Page<SanPhamCT> paginate(@RequestParam("p") int page) {
        return sanPhamCTService.getAll(PageRequest.of(page, 5));
    }

    @GetMapping("/search")
    @ResponseBody
    public Page<SanPhamCT> search(@RequestParam("keyword") String keyword, @RequestParam("p") int page) {
        return sanPhamCTService.search(keyword, PageRequest.of(page, 5));
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public SanPhamCT viewOne( String id) {
        return sanPhamCTService.getOne(id);
    }

    @GetMapping("/viewOneByMa")
    @ResponseBody
    public SanPhamCT viewOneByMa( @RequestParam("maSPCTSearch") String ma) {
        return sanPhamCTService.getOneByMa(ma);
    }

    @GetMapping("/filter")
    @ResponseBody
    public Page<SanPhamCT> filter(@RequestParam("danhMucSearch") String danhMucId,
                                  @RequestParam("kichThuocSearch") String kichThuocId,
                                  @RequestParam("mauSacSearch") String mauSacId,
                                  @RequestParam("thuongHieuSearch") String thuongHieuId,
                                  @RequestParam("p") int page) {

        return sanPhamCTService.searchByFilter(danhMucId, kichThuocId, mauSacId, thuongHieuId, PageRequest.of(page, 5));
    }
}
