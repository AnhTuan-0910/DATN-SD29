package com.springboot.bootstrap.controller;

import com.springboot.bootstrap.entity.*;
import com.springboot.bootstrap.entity.DTO.SanPhamQrDTO;
import com.springboot.bootstrap.repository.KhachHangRepository;
import com.springboot.bootstrap.repository.PhieuGiamGiaRepository;
import com.springboot.bootstrap.service.DanhMucService;
import com.springboot.bootstrap.service.HoaDonService;
import com.springboot.bootstrap.service.KhachHangService;
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

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

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
    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("")
    public String getAll(@RequestParam(value = "maVoucher", defaultValue = "PGG000") String ma,
                         @RequestParam(value = "sdtKhachHang", defaultValue = "0555555555") String sdt,
                         Model model) {
        List<DanhMuc> listDM = danhMucService.findAllByTrangThai();
        List<ThuongHieu> listTH = thuongHieuService.findAllByTrangThai();
        List<KichThuoc> listKT = kichThuocService.findAllByTrangThai();
        List<MauSac> listMS = mauSacService.findAllByTrangThai();
        List<PhieuGiamGia> listPGG = phieuGiamGiaRepository.findAll();
        PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findByMa(ma);
        KhachHang khachHang = khachHangService.findBySdt(sdt);
        List<KhachHang> listKH = khachHangService.findAll();
        List<HoaDon> listHD = hoaDonService.renderTab();
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("listKH", listKH);
        model.addAttribute("listHD", listHD);
        model.addAttribute("phieuGiamGia", phieuGiamGia);
        model.addAttribute("listVoucher", listPGG);
        model.addAttribute("listTH", listTH);
        model.addAttribute("listDM", listDM);
        model.addAttribute("listKT", listKT);
        model.addAttribute("listMS", listMS);
        model.addAttribute("spqr",new SanPhamQrDTO());
        return "/pages/giao_dich";
    }

    @PostMapping("/add_tab")
    public String addTab(@ModelAttribute("hda") HoaDon hoaDon) {
        hoaDon = HoaDon.builder().tinhTrang(4).build();
        hoaDonService.add(hoaDon);
        return "redirect:/giao_dich";
    }

    @GetMapping("/spct")
    @ResponseBody
    public Page<SanPhamCT> paginate(@RequestParam("p") int page) {
        return sanPhamCTService.getBySL(PageRequest.of(page, 5));
    }
    @GetMapping("/deleteTab/")
    public String deleteTab(@RequestParam("id") String id) {
        hoaDonService.delete(UUID.fromString(id));
        return "redirect:/giao_dich";
    }

    @GetMapping("/search")
    @ResponseBody
    public Page<SanPhamCT> search(@RequestParam("keyword") String keyword, @RequestParam("p") int page) {
        return sanPhamCTService.search(keyword, PageRequest.of(page, 5));
    }

    @GetMapping("/viewOne/")
    @ResponseBody
    public SanPhamCT viewOne(String id) {
        return sanPhamCTService.getOne(id);
    }

    @GetMapping("/viewOneByMa")
    @ResponseBody
    public SanPhamCT viewOneByMa(@RequestParam("maSPCTSearch") String ma) {
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
